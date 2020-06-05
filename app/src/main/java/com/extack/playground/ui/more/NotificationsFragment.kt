package com.extack.playground.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.extack.playground.databinding.FragmentNotificationsBinding
import com.extack.playground.di.Injector
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.ui.main.MainActivity
import com.extack.playground.utils.fragmentViewModels

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>
    (FragmentNotificationsBinding::inflate) {
    private val viewModel by fragmentViewModels {
        Injector.get().notificationVMFactory().get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fnSignOut.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }
    }
}