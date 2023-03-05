package by.ealipatov.gitapp.utils

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment


/***
 * Функция выывода на экран сообщений - "тостов" для фрагментов
 * На вход принимает строку
 */
fun Fragment.toast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

/***
 * Функция выывода на экран сообщений - "тостов" для активити
 * На вход принимает строку
 */
fun Activity.toast(string: String?) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}