package com.tunahanozatac.movieapp.di

import com.tunahanozatac.movieapp.data.network.ApiServices
import com.tunahanozatac.movieapp.data.repository.DetailsRepositoryImp
import com.tunahanozatac.movieapp.data.repository.MovieNowPlayingRepositoryImp
import com.tunahanozatac.movieapp.data.repository.UpComingRepositoryImp
import com.tunahanozatac.movieapp.domain.repository.DetailsRepository
import com.tunahanozatac.movieapp.domain.repository.ListRepository
import com.tunahanozatac.movieapp.domain.repository.UpComingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideSongApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideListRepository(api: ApiServices): ListRepository {
        return MovieNowPlayingRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideUpComingRepository(api: ApiServices): UpComingRepository {
        return UpComingRepositoryImp(api)
    }

    @Singleton
    @Provides
    fun provideDetailsRepository(api: ApiServices): DetailsRepository {
        return DetailsRepositoryImp(api)
    }
}