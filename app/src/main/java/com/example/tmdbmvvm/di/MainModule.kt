package com.example.tmdbmvvm.di

import com.example.tmdbmvvm.data.api.CallResponse
import com.example.tmdbmvvm.data.business.OnclickButton
import com.example.tmdbmvvm.data.repository.Repository_Imp
import com.example.tmdbmvvm.data.business.ResponseTreatment
import com.example.tmdbmvvm.main.adapter.MovieAdapter
import com.example.tmdbmvvm.main.view.HomeActivity
import com.example.tmdbmvvm.main.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single { ResponseTreatment(get()) }
    single { CallResponse() }
    single { HomeActivity() }
    single { OnclickButton() }
    single { Repository_Imp(get(), get()) }
    single { MovieAdapter() }

    viewModel { HomeViewModel(get()) }
}