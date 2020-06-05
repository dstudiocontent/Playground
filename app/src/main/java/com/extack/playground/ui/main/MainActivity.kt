package com.extack.playground.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.extack.playground.R
import com.extack.playground.databinding.ActivityMainBinding
import com.extack.playground.di.Injector
import com.extack.playground.model.Resource
import com.extack.playground.utils.hide
import com.extack.playground.utils.savedStateViewModels
import com.extack.playground.utils.show
import com.extack.playground.utils.showSnackbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    private val viewModel by savedStateViewModels { handle ->
        Injector.get().mainActivityVMFactory().create(handle)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        Log.v("ACTIVITY_TAG", "ON_CREATE_ACTIVITY at " + LocalDate.now())


        viewModel.getConfig().observe(this, Observer {
            when (it) {
                is Resource.SuccessSingle -> viewModel.setConfig(it.data)
                is Resource.Failure ->
                    showSnackbar(findViewById(R.id.nav_view), it.message)
            }
        })
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        val navGraphIds = listOf(
            R.navigation.home,
            R.navigation.notifications,
            R.navigation.dashboard
        )

        // Setup the bottom navigation view with a notifications of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            //setupActionBarWithNavController(navController)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                if (!viewModel.isUserSignedIn())
                    binding.navView.hide()
                else {
                    if (destination.id == R.id.home_fragment || destination.id == R.id.dashboard_fragment || destination.id == R.id.notifications_fragment) {
                        binding.navView.show()
                    } else binding.navView.hide()
                }
            }
        })
        currentNavController = controller

    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    inner class NetworkState {
        init {
            val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val builder = NetworkRequest.Builder()

            connectivityManager.registerDefaultNetworkCallback(object :
                ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    viewModel.setNetworkAvailable()
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    viewModel.setNetworkLost()
                }
            })
        }
    }
}