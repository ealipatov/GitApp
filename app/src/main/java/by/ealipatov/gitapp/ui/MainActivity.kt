package by.ealipatov.gitapp.ui

import android.os.Bundle
import by.ealipatov.gitapp.App
import by.ealipatov.gitapp.R
import by.ealipatov.gitapp.databinding.ActivityMainBinding
import by.ealipatov.gitapp.ui.navigation.OnBackPressedListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MyView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MyPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }

    override fun onPause() {
        App.instance.navigationHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}