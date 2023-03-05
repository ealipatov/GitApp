package by.ealipatov.gitapp.repository

import by.ealipatov.gitapp.UserEntityDTO

interface UsersRepository {
    //CRUD (Create, Read, Update, Delete)

    //Read
    fun getUsers(
        onSuccess: (List<UserEntityDTO>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}