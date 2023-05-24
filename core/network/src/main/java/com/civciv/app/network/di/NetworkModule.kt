/*
 * Copyright 2023 Fatih OZTURK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.civciv.app.network.di

import com.civciv.app.network.api.AccountsService
import com.civciv.app.network.api.AuthService
import com.civciv.app.network.api.MastodonService
import com.civciv.app.network.utils.Constants
import com.civciv.app.network.utils.DomainInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttp: OkHttpClient,
        gson: Gson,
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttp)
            .baseUrl(Constants.DOMAIN_PLACEHOLDER)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        domainInterceptor: DomainInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(domainInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Singleton
    @Provides
    fun provideAccountsService(
        retrofit: Retrofit,
    ): AccountsService = retrofit.create()

    @Singleton
    @Provides
    fun provideAuthService(
        retrofit: Retrofit,
    ): AuthService = retrofit.create()

    @Singleton
    @Provides
    fun provideMastodonService(
        retrofit: Retrofit,
    ): MastodonService = retrofit.create()
}
