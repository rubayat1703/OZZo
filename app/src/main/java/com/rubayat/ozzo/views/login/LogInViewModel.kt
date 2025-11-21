package com.rubayat.ozzo.views.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubayat.ozzo.Data.models.UserLogIn
import com.rubayat.ozzo.Data.repository.AuthRepository
import com.rubayat.ozzo.core.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authService : AuthRepository

): ViewModel() {

    private val _logInResponse = MutableLiveData<DataState<UserLogIn>>()
    val logInResponse: LiveData<DataState<UserLogIn>> = _logInResponse

    fun userLogin(user: UserLogIn){

         _logInResponse.postValue(DataState.Loading())

        authService.userlogin(user).addOnSuccessListener {
            _logInResponse.postValue(DataState.Success(user))


        }.addOnFailureListener {
            _logInResponse.postValue(DataState.Error((it.message.toString())))

        }
    }
}