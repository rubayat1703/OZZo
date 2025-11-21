package com.rubayat.ozzo.views.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.auth.User
import com.rubayat.ozzo.Data.models.UserRegistration
import com.rubayat.ozzo.Data.repository.AuthRepository
import com.rubayat.ozzo.core.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authService: AuthRepository

) : ViewModel(){

    private val _registrationResponse= MutableLiveData<DataState<UserRegistration>>()
    val registrationResponse : LiveData<DataState<UserRegistration>> = _registrationResponse

    fun userRegistration(user: UserRegistration ) {

        _registrationResponse.postValue(DataState.Loading())


        authService.userRegistration(user).addOnSuccessListener {
            it.user?.let { createdUser ->
                user.userId = createdUser.uid
                authService.createUser(user).addOnSuccessListener {
                    _registrationResponse.postValue(DataState.Success(user))

                }.addOnFailureListener { error ->
                    _registrationResponse.postValue(DataState.Error("${error.message}"))

                }
            }
        }.addOnFailureListener { error ->

            _registrationResponse.postValue(DataState.Error("${error.message}"))

        }
    }


}