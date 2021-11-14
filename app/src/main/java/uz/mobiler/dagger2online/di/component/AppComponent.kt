package uz.mobiler.dagger2online.di.component

import dagger.Component
import uz.mobiler.dagger2online.MainActivity
import uz.mobiler.dagger2online.di.module.DatabaseModule
import uz.mobiler.dagger2online.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}