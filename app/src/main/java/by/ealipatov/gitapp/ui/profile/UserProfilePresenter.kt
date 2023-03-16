package by.ealipatov.gitapp.ui.profile

import by.ealipatov.gitapp.domain.UsersRepository
import moxy.MvpPresenter

class UserProfilePresenter(
    private val usersRepository: UsersRepository,
    ): MvpPresenter<UserProfileView>() {

    private var isProgress: Boolean = false

    fun loadUserProfile(position: Int) {

        viewState.showProgress(true)
        isProgress = true
        usersRepository.getUsers(
            onSuccess = {
                viewState.showProgress(false)
                viewState.showUserProfile(it[position])
                isProgress = false
            },
            onError = {
                viewState.showProgress(false)
                viewState.showError(it)
                isProgress = false
            }
        )
    }
}