package by.ealipatov.gitapp.data

import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.domain.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET


interface GitHubApi {

    @GET("users")
    fun getUsers(): Call<List<UserEntityDTO>>

}

class RetrofitUserRepositoryImpl : UsersRepository {

    override fun getUsers(
        onSuccess: (List<UserEntityDTO>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        val apiInterface: GitHubApi = ApiClient().retrofit.create(GitHubApi::class.java)
        val call: Call<List<UserEntityDTO>> = apiInterface.getUsers()
        call.enqueue(object : Callback<List<UserEntityDTO>> {
            override fun onResponse(
                call: Call<List<UserEntityDTO>>,
                response: Response<List<UserEntityDTO>>
            ) {

                val users: List<UserEntityDTO> = response.body() as List<UserEntityDTO>

                onSuccess(users)
            }

            override fun onFailure(call: Call<List<UserEntityDTO>>, t: Throwable) {
            }

        })

    }
}
