package com.extack.playground.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding
) : Fragment() {

    private var mInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding = inflater

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var activityVM: ActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityVM = ViewModelProvider(requireActivity())[ActivityVM::class.java]
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