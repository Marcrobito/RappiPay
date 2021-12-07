package dev.eighteentech.rappipay.di

import dev.eighteentech.rappipay.model.MovieRepository
import dev.eighteentech.rappipay.network.NetworkService
import dev.eighteentech.rappipay.ui.DetailViewModel
import dev.eighteentech.rappipay.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { NetworkService.service }
    single { MovieRepository(get()) }
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val detailModule = module {
    viewModel { DetailViewModel(get()) }
}