package com.extack.playground.ui.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.extack.playground.databinding.FragmentSignInBinding
import com.extack.playground.repo.helper.StatusCode
import com.extack.playground.ui.main.BaseFragment
import com.extack.playground.utils.hideKeyboardFrom
import com.extack.playground.utils.showSnackbar

class SignInFragment : BaseFragment<FragmentSignInBinding>(
    FragmentSignInBinding::inflate
) {

    private val viewModel: SignInViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fsiSignIn.setButtonOnClickListener(View.OnClickListener {
            hideKeyboardFrom(requireActivity(), binding.fsiPassword)
            viewModel.registerUser(
                binding.fsiEmail.text.toString(),
                binding.fsiPassword.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                if (it.status == StatusCode.SUCCESS) {
                    findNavController().navigate(SignInFragmentDirections.signInToHome())
                } else
                    showSnackbar(binding.root, it.message)
                binding.fsiSignIn.progressCompleted()
            })
        })

        binding.fsiSignupLink.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.signInToUp())
        }
    }
}