package com.example.myapplication.ui.movieList

import com.example.myapplication.data.MovieLocalDataSource
import com.example.myapplication.data.MovieRemoteDataSource
import com.example.myapplication.data.MovieRepository
import com.example.myapplication.data.dataBase.MYDataBase
import com.example.myapplication.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single { MovieRepository(get(), get()) }

    single { MovieLocalDataSource(get(),get()) }

    single { MovieRemoteDataSource(get()) }

    single {
        val retrofit = get() as Retrofit
        val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
        retrofitService
    }
    single {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://api.themoviedb.org/3/")
            .build()
        retrofit
    }

    viewModel {
        MovieListViewModel(get())
    }




    single { MYDataBase.getAppDataBase(androidContext()) }
    single { get<MYDataBase>().movieDao() }
    single { get<MYDataBase>().videoDao() }


}