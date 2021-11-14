package uz.mobiler.dagger2online

import android.app.Application
import uz.mobiler.dagger2online.di.component.AppComponent
import uz.mobiler.dagger2online.di.component.DaggerAppComponent
import uz.mobiler.dagger2online.di.module.DatabaseModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(this))
            .build()
    }
}