package uz.mobiler.dagger2online.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.mobiler.dagger2online.database.dao.UserDao
import uz.mobiler.dagger2online.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}