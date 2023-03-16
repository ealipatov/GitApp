package by.ealipatov.gitapp.ui.users

import by.ealipatov.gitapp.domain.UserEntityDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {

    fun showUsers(users: List<UserEntityDTO>)
    fun showError(throwable: Throwable)
    fun showProgress(isProgress: Boolean)
}
