package uz.mobiler.dagger2online.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.mobiler.dagger2online.database.entity.UserEntity
import uz.mobiler.dagger2online.repository.UserRepository
import uz.mobiler.dagger2online.utils.NetworkHelper
import uz.mobiler.dagger2online.utils.UserResource
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val stateFlow = MutableStateFlow<UserResource>(UserResource.Loading)

    init {
        getUsers()
    }

    private fun getUsers() {
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                userRepository.getUsers()
                    .catch {
                        stateFlow.value = UserResource.Error(it.message ?: "")
                    }.collect {
                        if (it.isSuccessful) {
                            val list = ArrayList<UserEntity>()
                            it.body()?.forEach {
                                val userEntity = UserEntity(it.id, it.name, it.username, it.email)
                                list.add(userEntity)
                            }
                            userRepository.insertUsers(list)
                            stateFlow.value = UserResource.Success(userRepository.getDbUsers())
                        } else {

                        }
                    }
            }
        } else {
            stateFlow.value = UserResource.Error("Internet not connected")
        }
    }

    fun getStateFlow(): StateFlow<UserResource> {
        return stateFlow
    }
}