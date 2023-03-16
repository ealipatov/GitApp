package by.ealipatov.gitapp.ui

import by.ealipatov.gitapp.ui.navigation.UsersScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MyPresenter(
    private val router: Router
) : MvpPresenter<MyView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(UsersScreens)
    }

    fun onBackPressed() {
        router.exit()
    }

}