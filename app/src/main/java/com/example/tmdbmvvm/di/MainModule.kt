package com.example.tmdbmvvm.di

import com.example.tmdbmvvm.data.response.CallResponse
import com.example.tmdbmvvm.data.business.OnclickButton
import com.example.tmdbmvvm.repository.RepositoryImplement
import com.example.tmdbmvvm.data.response.ResponseTreatment
import com.example.tmdbmvvm.model.GeneroModel.GenreCache
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
    single { RepositoryImplement(get()) }
    single { MovieAdapter() }
    single { GenreCache }

    viewModel { HomeViewModel(get()) }
}