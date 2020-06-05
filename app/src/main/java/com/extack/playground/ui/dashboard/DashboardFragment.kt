package com.extack.playground.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.extack.playground.databinding.FragmentDashboardBinding
import com.extack.playground.di.Injector
import com.extack.playground.model.Resource
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.utils.fragmentViewModels
import com.extack.playground.utils.hide

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {
    private val viewModel by fragmentViewModels {
        Injector.get().dashboardyVMFactory().get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!viewModel.isUserSignedIn()) binding.fetchLatestRates.hide()
        else {
            binding.fetchLatestRates.setButtonOnClickListener(View.OnClickListener {
                viewModel.getRates().observe(viewLifecycleOwner, Observer {
                    binding.fetchLatestRates.progressCompleted()
                    when (it) {
                        is Resource.SuccessSingle -> Log.w("_TAG", it.data.base)
                        is Resource.Failure -> Log.w("_TAG", it.message)
                    }
                })
            })
        }
    }
}