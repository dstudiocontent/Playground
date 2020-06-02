package com.extack.playground.ui.more

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.extack.playground.databinding.FragmentNotificationsBinding
import com.extack.playground.model.Resource
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.ui.main.MainActivity

class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>
    (FragmentNotificationsBinding::inflate) {
    private val viewModel: NotificationsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRates().observe(this, Observer {
            when (it) {
                is Resource.SuccessSingle -> Log.w("_TAG", it.data.base)
                is Resource.Failure -> Log.w("_TAG", it.message)
            }
        })
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