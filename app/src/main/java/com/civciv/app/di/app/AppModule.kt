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
package com.civciv.app.di.app

import android.app.Application
import android.content.Context
import com.civciv.app.R
import com.civciv.app.base.inject.ApplicationId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationId
    @Provides
    fun provideApplicationId(application: Application): String = application.packageName

    @Singleton
    @Named("civciv-redirect-uri")
    @Provides
    fun provideAuthRedirectUri(
        @ApplicationId applicationId: String,
        @ApplicationContext context: Context,
    ): String = "$applicationId://${context.getString(R.string.oauth_scheme)}"
}
