package com.extack.playground.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.extack.playground.databinding.FragmentDashboardBinding
import com.extack.playground.repo.helper.FirebaseAuthHelper
import com.extack.playground.ui.main.BaseFragment

class DashboardFragment :
    BaseFragment<FragmentDashboardBinding>(FragmentDashboardBinding::inflate) {

    private val authHelper = FirebaseAuthHelper()
    private val viewModel: DashboardViewModel by viewModels {
        DashboardVMFactory(
            authHelper,
            this
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.pr()
    }
}