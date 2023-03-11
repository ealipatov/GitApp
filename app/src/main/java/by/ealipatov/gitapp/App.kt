package by.ealipatov.gitapp

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import by.ealipatov.gitapp.data.RetrofitUserRepositoryImpl
import by.ealipatov.gitapp.domain.UsersRepository

class App : Application() {
   // val usersRepository: UsersRepository by lazy { LocalUsersRepositoryImpl() }
    val usersRepository: UsersRepository by lazy { RetrofitUserRepositoryImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App