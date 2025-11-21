package com.rubayat.ozzo.views.login

import android.content.Intent
import com.rubayat.ozzo.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.rubayat.ozzo.Base.BaseFragment
import com.rubayat.ozzo.Data.models.UserLogIn
import com.rubayat.ozzo.core.DataState
import com.rubayat.ozzo.databinding.FragmentLogInBinding
import com.rubayat.ozzo.isEmpty
import com.rubayat.ozzo.views.dashboard.seller.seller_dashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInFragment : BaseFragment<FragmentLogInBinding>(FragmentLogInBinding::inflate) {

    private val viewModel: LogInViewModel by viewModels()



    override fun setListener() {

        with(binding) {
            btnLogin.setOnClickListener {
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etEmail.isEmpty() && !etPassword.isEmpty()) {
                    val user = UserLogIn(etEmail.text.toString(), etPassword.text.toString())

                    viewModel.userLogin(user)
                    loading.show()
                }
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    R.id.action_loginFragment_to_registerFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()
                )
            }


        }
    }

    override fun allObserver() {
        logInResponse()

    }

    private fun logInResponse() {
        viewModel.logInResponse.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context, "Loading....", Toast.LENGTH_SHORT).show()
                }

                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context, "LogIn Successful", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(requireContext(), seller_dashboard::class.java))
                    requireActivity().finish()


                }
            }


        }
    }

}


