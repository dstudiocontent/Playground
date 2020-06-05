package com.extack.playground.ui.auth.signup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.extack.playground.databinding.FragmentSignUpBinding
import com.extack.playground.di.Injector
import com.extack.playground.repo.helper.StatusCode
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.utils.hideKeyboardFrom
import com.extack.playground.utils.savedStateViewModels
import com.extack.playground.utils.showSnackbar

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
) {
    private val viewModel by savedStateViewModels { handle ->
        Injector.get().signUpVMFactory().create(handle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fsuSignUp.setButtonOnClickListener(View.OnClickListener {
            hideKeyboardFrom(requireActivity(), binding.fsuPassword)
            viewModel.registerUser(
                binding.fsuEmail.text.toString(),
                binding.fsuPassword.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                if (it.status == StatusCode.SUCCESS) {
                    findNavController().navigate(SignUpFragmentDirections.signUpToHome())
                } else
                    showSnackbar(binding.root, it.message)
                binding.fsuSignUp.progressCompleted()
            })
        })

        binding.fsuSigninLink.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.signUpToIn())
        }
    }
}