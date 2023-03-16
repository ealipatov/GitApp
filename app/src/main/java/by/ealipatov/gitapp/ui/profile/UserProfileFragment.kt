package by.ealipatov.gitapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import by.ealipatov.gitapp.App
import by.ealipatov.gitapp.R
import by.ealipatov.gitapp.app
import by.ealipatov.gitapp.databinding.FragmentUserProfileBinding
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.ui.navigation.OnBackPressedListener
import by.ealipatov.gitapp.ui.navigation.UsersScreens
import by.ealipatov.gitapp.utils.toast
import coil.load
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserProfileFragment(position: Int) : MvpAppCompatFragment(), UserProfileView,
    OnBackPressedListener {
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val presenter: UserProfilePresenter by moxyPresenter {
        UserProfilePresenter(app.usersRepository)
    }
    val pos = position

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserProfileBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.loadUserProfile(pos)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        App.instance.router.backTo(UsersScreens)
        return true
    }

    override fun showUserProfile(user: UserEntityDTO) {
        with(binding) {
            showProgress(false)
            displayUserAvatar(user.avatarUrl)
            userLoginTextView.text = user.login
            userTypeTextView.text = user.type
        }
    }

    override fun showError(throwable: Throwable) {
        toast(throwable.message)
    }

    override fun showProgress(isProgress: Boolean) {
        binding.progressBar.isVisible = isProgress
        binding.userProfileItemView.isVisible = !isProgress
    }

    /***
     * Функция вывода с помощью Coil картинки.
     * На вход принимает url в виде строки.
     */
    private fun displayUserAvatar(url: String) {
        binding.userAvatarImageView.load(url)
        {
            error(R.drawable.baseline_no_photography_24)
            placeholder(R.drawable.loadingfast)
            crossfade(true)
        }
    }
}