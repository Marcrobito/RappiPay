package dev.eighteentech.rappipay.di

import dev.eighteentech.rappipay.detail.DetailRepository
import dev.eighteentech.rappipay.detail.DetailRepositoryImpl
import dev.eighteentech.rappipay.home.MovieRepositoryImpl
import dev.eighteentech.rappipay.search.SearchRepositoryImpl
import dev.eighteentech.rappipay.network.NetworkService
import dev.eighteentech.rappipay.detail.DetailViewModel
import dev.eighteentech.rappipay.home.MainViewModel
import dev.eighteentech.rappipay.home.MovieRepository
import dev.eighteentech.rappipay.search.SearchRepository
import dev.eighteentech.rappipay.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { NetworkService.service }
}

val mainModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    viewModel { MainViewModel(get()) }
}

val detailModule = module {
    single<DetailRepository> { DetailRepositoryImpl(get()) }
    viewModel { DetailViewModel(get()) }
}

val searchModule = module {
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    viewModel { SearchViewModel(get()) }
}