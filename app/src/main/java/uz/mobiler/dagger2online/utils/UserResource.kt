package uz.mobiler.dagger2online.utils

import uz.mobiler.dagger2online.database.entity.UserEntity

sealed class UserResource {

    object Loading : UserResource()

    data class Success(val list: List<UserEntity>) : UserResource()

    data class Error(val message: String) : UserResource()
}