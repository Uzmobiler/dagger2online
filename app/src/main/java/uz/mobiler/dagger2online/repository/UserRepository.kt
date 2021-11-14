package uz.mobiler.dagger2online.repository

import kotlinx.coroutines.flow.flow
import uz.mobiler.dagger2online.database.dao.UserDao
import uz.mobiler.dagger2online.database.entity.UserEntity
import uz.mobiler.dagger2online.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) {
    suspend fun getUsers() = flow { emit(apiService.getUsers()) }

    suspend fun insertUsers(list: List<UserEntity>) = userDao.addAll(list)

    suspend fun getDbUsers() = userDao.getUsers()
}