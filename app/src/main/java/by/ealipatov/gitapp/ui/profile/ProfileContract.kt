package by.ealipatov.gitapp.ui.profile

import by.ealipatov.gitapp.domain.UserEntityDTO

interface ProfileContract {

    interface View {
        fun showUserProfile(user: UserEntityDTO)
        fun showError(throwable: Throwable)
        fun showProgress(isProgress: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()

        fun loadUserProfile(position: Int)

    }
}