package by.ealipatov.gitapp.data

import android.os.Handler
import android.os.Looper
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.domain.UsersRepository
import by.ealipatov.gitapp.utils.DATA_LOADING_LOCAL_DELAY

class LocalUsersRepositoryImpl: UsersRepository {

    private val data: List<UserEntityDTO> = listOf(
        UserEntityDTO("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        UserEntityDTO("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        UserEntityDTO("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        UserEntityDTO("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        UserEntityDTO("ezmobius", 5, "https://avatars.githubusercontent.com/u/5?v=4"),
        UserEntityDTO("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        UserEntityDTO("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        UserEntityDTO("vanpelt", 17, "https://avatars.githubusercontent.com/u/17?v=4"),
        UserEntityDTO("wayneeseguin", 18, "https://avatars.githubusercontent.com/u/18?v=4"),
        UserEntityDTO("brynary", 19, "https://avatars.githubusercontent.com/u/19?v=4")
    )

    override fun getUsers(
        onSuccess: (List<UserEntityDTO>) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({onSuccess(data)},
            DATA_LOADING_LOCAL_DELAY
        )
    }
}