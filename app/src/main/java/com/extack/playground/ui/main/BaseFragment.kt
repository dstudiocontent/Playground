package com.extack.playground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.extack.playground.di.Injector
import com.extack.playground.utils.savedStateActivityViewModels

abstract class BaseFragment<VB : ViewBinding>(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
) : Fragment() {

    private var mInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding = inflater

    private var _binding: VB? = null
    val binding get() = _binding!!

    val activityViewModel by savedStateActivityViewModels { handle ->
        Injector.get().mainActivityVMFactory().create(handle)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNCHECKED_CAST")
        _binding = mInflater.invoke(layoutInflater, container, false) as VB
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}