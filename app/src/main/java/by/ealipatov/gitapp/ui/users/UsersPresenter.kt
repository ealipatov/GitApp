package by.ealipatov.gitapp.ui.users

import by.ealipatov.gitapp.domain.UsersRepository
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter


class UsersPresenter(
    private val repository: UsersRepository,
    private val router: Router
) : MvpPresenter<UsersView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadData() {
        repository.getUsers(
            onSuccess = {
                viewState.showProgress(false)
                viewState.showUsers(it)
            },
            onError = {
                viewState.showProgress(false)
                viewState.showError(it)
            }
        )
    }
}
