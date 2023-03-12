package by.ealipatov.gitapp.ui.users

import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.domain.UsersRepository

class UsersPresenter(private val usersRepository: UsersRepository) : UsersContract.Presenter {
    private var view: UsersContract.View? = null

    private var userList: List<UserEntityDTO>? = null
    private var isProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(isProgress)
        userList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshData() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        isProgress = true
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
                userList = it
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