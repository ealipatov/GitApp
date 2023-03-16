package by.ealipatov.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import by.ealipatov.gitapp.data.RetrofitUserRepositoryImpl
import by.ealipatov.gitapp.domain.UsersRepository
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object{
        lateinit var instance: App
    }

   // val usersRepository: UsersRepository by lazy { LocalUsersRepositoryImpl() }
    val usersRepository: UsersRepository by lazy { RetrofitUserRepositoryImpl() }

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create()}
    val navigationHolder = cicerone.getNavigatorHolder()
    val router = cicerone.router


    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App