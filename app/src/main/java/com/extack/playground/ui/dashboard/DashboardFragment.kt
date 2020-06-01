package com.extack.playground.ui.dashboard

import com.extack.playground.databinding.FragmentDashboardBinding
import com.extack.playground.ui.BaseFragment
import com.extack.playground.viewmodel.DashboardViewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding, DashboardViewModel>(
    FragmentDashboardBinding::inflate, DashboardViewModel::class.java
)