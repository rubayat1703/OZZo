package com.rubayat.ozzo.Data.models

data class UserRegistration(
    val name : String,
    val email : String,
    val password : String,
    val userType : String,
    var userId : String
)
