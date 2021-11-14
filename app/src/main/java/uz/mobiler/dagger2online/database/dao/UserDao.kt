package uz.mobiler.dagger2online.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import uz.mobiler.dagger2online.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userEntity: UserEntity)

    @Query("select * from userentity")
    suspend fun getUsers(): List<UserEntity>

    @Insert
    suspend fun addAll(list: List<UserEntity>)
}