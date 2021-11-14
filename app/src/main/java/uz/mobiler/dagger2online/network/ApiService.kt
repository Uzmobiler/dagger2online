package uz.mobiler.dagger2online.network

import retrofit2.Response
import retrofit2.http.GET
import uz.mobiler.dagger2online.models.UserData

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<UserData>>
}