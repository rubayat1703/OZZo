package com.rubayat.ozzo.views.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rubayat.ozzo.Base.BaseFragment
import com.rubayat.ozzo.Data.models.UserRegistration
import com.rubayat.ozzo.core.DataState
import com.rubayat.ozzo.databinding.FragmentRegisterBinding
import com.rubayat.ozzo.isEmpty
import com.rubayat.ozzo.views.dashboard.seller.seller_dashboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    private val viewModel: RegistrationViewModel by viewModels ()


    override fun setListener() {
        with(binding){
            btnRegister.setOnClickListener {
                etName.isEmpty()
                etEmail.isEmpty()
                etPassword.isEmpty()
                if (!etName.isEmpty() && !etEmail.isEmpty() && !etPassword.isEmpty()){
                    Toast.makeText(context, "All input done...", Toast.LENGTH_LONG).show()

                    val user= UserRegistration(
                        etName.text.toString(),
                        etEmail.text.toString(),
                        etPassword.text.toString(),
                        userType = "Seller",
                        userId = ""

                    )

                    viewModel.userRegistration(user)

                }
            }
        }

    }

    override fun allObserver() {
        registrationResponse()
    }

    private fun registrationResponse() {

        viewModel.registrationResponse.observe(viewLifecycleOwner){

            when(it){
                is DataState.Error -> {
                    loading.dismiss()
                    Toast.makeText(context,it.message, Toast.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loading.show()
                    Toast.makeText(context,"Loading....", Toast.LENGTH_LONG).show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Toast.makeText(context,"Created user ${it.data}", Toast.LENGTH_LONG).show()

                    startActivity(Intent(requireContext(), seller_dashboard::class.java))
                    requireActivity().finish()

                }
            }
        }

    }

}