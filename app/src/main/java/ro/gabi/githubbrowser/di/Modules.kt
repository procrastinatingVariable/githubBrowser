package ro.gabi.githubbrowser.di

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.gabi.githubbrowser.data.repository.RepoRepository
import ro.gabi.githubbrowser.data.repository.RepoRepositoryImpl
import ro.gabi.githubbrowser.features.common.RecyclerViewAdapterFactory
import ro.gabi.githubbrowser.features.repolist.RepositoryListViewModel
import ro.gabi.githubbrowser.network.ApiClient
import ro.gabi.githubbrowser.network.ApiService
import ro.gabi.githubbrowser.network.interceptors.JsonBodyLoggerInterceptor

val appModule = module {

}


val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(getProperty<String>("github_api_url"))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    factory {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    factory {
        HttpLoggingInterceptor(JsonBodyLoggerInterceptor).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    } bind Interceptor::class


    factory <ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }

    single { ApiClient(get()) }
}

val commonModule = module {

    single { RecyclerViewAdapterFactory() }

}

val dataModule = module {
    single { RepoRepositoryImpl(get()) } bind RepoRepository::class
}

val viewModelModule = module {

    viewModel { RepositoryListViewModel(get()) }

}