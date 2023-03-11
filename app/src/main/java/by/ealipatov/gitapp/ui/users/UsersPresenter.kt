package by.ealipatov.gitapp.ui.users

import by.ealipatov.gitapp.domain.UsersRepository

class UsersPresenter(private val usersRepository: UsersRepository) : UsersContract.Presenter {
    private var view: UsersContract.View? = null

    override fun attach(view: UsersContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun onRefreshData() {
        loadData()
    }

    private fun loadData() {
        view?.showProgress(true)
        usersRepository.getUsers(
            onSuccess = {
                view?.showProgress(false)
                view?.showUsers(it)
            },
            onError = {
                view?.showProgress(false)
                view?.showError(it)
            }
        )
    }
}