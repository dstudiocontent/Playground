package com.extack.playground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.extack.playground.viewmodel.ActivityVM

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(
    inflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding,
    type: Class<VM>
) : Fragment() {

    private var mType: Class<VM> = type

    private var mInflater: (LayoutInflater, ViewGroup?, Boolean) -> ViewBinding = inflater

    private var _binding: VB? = null
    val binding get() = _binding!!

    lateinit var viewModel: VM
    lateinit var activityVM: ActivityVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[mType]
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