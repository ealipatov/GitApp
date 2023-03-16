package by.ealipatov.gitapp.ui.profile

import by.ealipatov.gitapp.domain.UserEntityDTO
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserProfileView : MvpView {

    fun showUserProfile(user: UserEntityDTO)
    fun showError(throwable: Throwable)
    fun showProgress(isProgress: Boolean)
}