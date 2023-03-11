package by.ealipatov.gitapp.ui.users

import by.ealipatov.gitapp.domain.UserEntityDTO

interface UsersContract {

    interface View {
        fun showUsers(users: List<UserEntityDTO>)
        fun showError(throwable: Throwable)
        fun showProgress(isProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun onRefreshData()

    }

}