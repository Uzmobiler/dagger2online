package uz.mobiler.dagger2online.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import uz.mobiler.dagger2online.database.dao.UserDao
import uz.mobiler.dagger2online.database.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule(var context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context


    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
}