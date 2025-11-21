package com.rubayat.ozzo.dip

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.rubayat.ozzo.Data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.processor.internal.definecomponent.codegen._dagger_hilt_components_SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()

    }
    @Provides
    @Singleton
    fun providesFirebaseFirestoreDB(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()

    }

    @Provides
    @Singleton
    fun providesFirebase(jAuth: FirebaseAuth,db: FirebaseFirestore): AuthRepository{
        return AuthRepository(jAuth,db)

    }
}