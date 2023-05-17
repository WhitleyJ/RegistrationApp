package com.reg.registrationapp.di

import android.app.Application
import android.content.Context
import com.reg.registrationapp.core.util.Constance.BASE_URL
import com.reg.registrationapp.data.remote.ApiService
import com.reg.registrationapp.data.repositoryImpl.RegistrationRepositoryImpl
import com.reg.registrationapp.domain.repository.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    companion object {
        @Singleton
        @Provides
        fun provideApiFindFriends(): ApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        @Provides
        fun provideContext(context: Application) : Context {
            return context.applicationContext
        }
    }

    @Singleton
    @Binds
    fun providesRepository(impl: RegistrationRepositoryImpl) : RegistrationRepository
}
