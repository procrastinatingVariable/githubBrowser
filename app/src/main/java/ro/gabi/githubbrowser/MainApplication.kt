package ro.gabi.githubbrowser

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin
import ro.gabi.githubbrowser.di.*

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidFileProperties()
            modules(listOf(appModule, networkModule, dataModule, commonModule, viewModelModule))
        }
    }
}