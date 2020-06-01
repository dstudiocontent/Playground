package com.extack.playground.ui.more

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.extack.playground.MainActivity
import com.extack.playground.databinding.FragmentNotificationsBinding
import com.extack.playground.ui.BaseFragment
import com.extack.playground.viewmodel.NotificationsViewModel

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding, NotificationsViewModel>
    (FragmentNotificationsBinding::inflate, NotificationsViewModel::class.java) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fnSignOut.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(requireActivity(), MainActivity::class.java))
            requireActivity().finishAffinity()
        }
    }
}