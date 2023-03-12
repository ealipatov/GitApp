package by.ealipatov.gitapp.ui.profile

import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.domain.UsersRepository

class UserProfilePresenter(private val usersRepository: UsersRepository): ProfileContract.Presenter {

    private var view: ProfileContract.View? = null

    private var userProfile: UserEntityDTO? = null
    private var isProgress: Boolean = false

    override fun attach(view: ProfileContract.View) {
        this.view = view
        view.showProgress(isProgress)
        userProfile?.let { view.showUserProfile(it) }

    }

    override fun detach() {
        view = null
    }

    override fun loadUserProfile(position: Int) {

        view?.showProgress(true)
        isProgress = true
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUserProfile(it[position])
                isProgress = false
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
                isProgress = false
            }
        )
    }
}