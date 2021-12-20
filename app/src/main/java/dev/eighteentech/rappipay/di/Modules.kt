package dev.eighteentech.rappipay.di

import dev.eighteentech.rappipay.model.DetailRepository
import dev.eighteentech.rappipay.model.MovieRepository
import dev.eighteentech.rappipay.model.SearchRepository
import dev.eighteentech.rappipay.network.NetworkService
import dev.eighteentech.rappipay.ui.DetailViewModel
import dev.eighteentech.rappipay.ui.MainViewModel
import dev.eighteentech.rappipay.ui.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { NetworkService.service }
}

val mainModule = module {
    single { MovieRepository(get()) }
    viewModel { MainViewModel(get()) }
}

val detailModule = module {
    single { DetailRepository(get()) }
    viewModel { DetailViewModel(get()) }
}

val searchModule = module {
    single { SearchRepository(get()) }
    viewModel { SearchViewModel(get()) }
}