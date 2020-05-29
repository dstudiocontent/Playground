package com.extack.kotlinapp.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.extack.kotlinapp.databinding.FragmentHomeBinding
import com.extack.kotlinapp.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        if (!::rootView.isInitialized) {
            Log.v("FRAGMENT_TAG", "ROOT_VIEW_CREATED")
            homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
            rootView = binding.root
            val textView: TextView = binding.textHome
            homeViewModel.text.observe(viewLifecycleOwner, Observer {
                Handler().postDelayed(Runnable {
                    textView.text = it
                },2500)
            })
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonHome.setOnClickListener {
            val action =
                HomeFragmentDirections.actionHomeToSecond()
            NavHostFragment.findNavController(this@HomeFragment)
                .navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}