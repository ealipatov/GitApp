package by.ealipatov.gitapp.domain

interface UsersRepository {
    //CRUD (Create, Read, Update, Delete)

    //Read
    fun getUsers(
        onSuccess: (List<UserEntityDTO>) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}