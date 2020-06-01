package com.extack.playground.ui.home

import android.os.Bundle
import android.view.View
import com.extack.playground.databinding.FragmentHomeSecondBinding
import com.extack.playground.ui.BaseFragment
import com.extack.playground.viewmodel.HomeSecondVM

class HomeSecondFragment : BaseFragment<FragmentHomeSecondBinding, HomeSecondVM>(
    FragmentHomeSecondBinding::inflate, HomeSecondVM::class.java
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}