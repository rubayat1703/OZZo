package com.rubayat.ozzo.Data.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.rubayat.ozzo.Data.models.UserLogIn
import com.rubayat.ozzo.Data.models.UserRegistration
import com.rubayat.ozzo.Data.services.AuthServices
import com.rubayat.ozzo.core.Nodes
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val jAuth : FirebaseAuth,
    private val db: FirebaseFirestore
) : AuthServices{
    override fun userRegistration(user: UserRegistration): Task<AuthResult> {

         return jAuth.createUserWithEmailAndPassword(user.email,user.password)

    }
    override fun userlogin(user: UserLogIn): Task<AuthResult> {

        return jAuth.signInWithEmailAndPassword(user.email,user.password)

    }
    override fun createUser(user: UserRegistration):Task<Void> {
        return db.collection(Nodes.USER).document(user.userId).set(user)

    }
}