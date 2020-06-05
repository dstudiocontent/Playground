package com.extack.playground.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.extack.playground.databinding.FragmentHomeBinding
import com.extack.playground.databinding.RvItemUserCardBinding
import com.extack.playground.di.Injector
import com.extack.playground.model.Resource
import com.extack.playground.model.firestore.Rates
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.utils.fragmentViewModels
import com.extack.playground.utils.showSnackbar

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel by fragmentViewModels {
        Injector.get().homeVMFactory().get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.w("_TAG_X", viewModel.isUserLoggedIn().toString())
        if (!viewModel.isUserLoggedIn()) {
            findNavController().navigate(HomeFragmentDirections.homeToSignIn())
        } else {
            activityViewModel.getSavedConfig().observe(viewLifecycleOwner, Observer {
                viewModel.getLatestRates(it.updatedDate)
                    .observe(viewLifecycleOwner, Observer { userResource ->
                        when (userResource) {
                            is Resource.SuccessSingle ->
                                binding.rvUsers.apply {
                                    adapter = UserRVAdapter(listOf(userResource.data))
                                    layoutManager = LinearLayoutManager(
                                        requireActivity(),
                                        LinearLayoutManager.VERTICAL,
                                        false
                                    )
                                }
                            is Resource.Failure -> showSnackbar(binding.root, userResource.message)
                        }
                    })
            })
        }
    }

    inner class UserRVAdapter(private val users: List<Rates>) :
        RecyclerView.Adapter<UserRVAdapter.VH>() {

        inner class VH(private val itemViewBinding: RvItemUserCardBinding) :
            RecyclerView.ViewHolder(itemViewBinding.root) {
            init {
                Log.w("_TAG", "ViewHolder Init")
                itemView.setOnClickListener {

                }
            }

            fun bind(rates: Rates) {
                itemViewBinding.ucId.text = rates.base
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
            val itemViewBinding =
                RvItemUserCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return VH(itemViewBinding)
        }

        override fun onBindViewHolder(holder: VH, position: Int) {
            holder.bind(users[position])
        }

        override fun getItemCount(): Int {
            return users.size
        }
    }

}