package com.rubayat.ozzo.Data.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.rubayat.ozzo.Data.models.UserLogIn
import com.rubayat.ozzo.Data.models.UserRegistration

interface AuthServices {

    fun userRegistration(user: UserRegistration): Task<AuthResult>
    fun userlogin(user: UserLogIn): Task<AuthResult>
    fun createUser(user: UserRegistration):Task<Void>
}