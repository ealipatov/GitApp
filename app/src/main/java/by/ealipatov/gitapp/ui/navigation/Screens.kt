package by.ealipatov.gitapp.ui.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import by.ealipatov.gitapp.ui.profile.UserProfileFragment
import by.ealipatov.gitapp.ui.users.UsersListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreens : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersListFragment.getInstance()
    }

    fun userProfile(position: Int) = FragmentScreen { UserProfileFragment(position) }

}