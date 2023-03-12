package by.ealipatov.gitapp.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.ealipatov.gitapp.R
import by.ealipatov.gitapp.app
import by.ealipatov.gitapp.databinding.ActivityProfileBinding
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.utils.toast
import coil.load

class UserProfileActivity: AppCompatActivity(), ProfileContract.View {

    private var _binding: ActivityProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: ProfileContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position",0)

        presenter = extractPresenter()
        presenter.attach(this)

        presenter.loadUserProfile(position)
    }

    override fun onDestroy() {
        presenter.detach()
        _binding = null
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): ProfileContract.Presenter {
        return presenter
    }
    private fun  extractPresenter() : ProfileContract.Presenter{
        return  lastCustomNonConfigurationInstance as? ProfileContract.Presenter
            ?: UserProfilePresenter(app.usersRepository)
    }

    override fun showUserProfile(user: UserEntityDTO) {
        with(binding){
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