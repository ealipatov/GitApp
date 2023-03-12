package by.ealipatov.gitapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.ealipatov.gitapp.app
import by.ealipatov.gitapp.databinding.ActivityMainBinding
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.ui.users.UsersAdapter
import by.ealipatov.gitapp.ui.users.UsersContract
import by.ealipatov.gitapp.ui.users.UsersPresenter
import by.ealipatov.gitapp.utils.toast

class MainActivity : AppCompatActivity(), UsersContract.View {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val adapter = UsersAdapter()

    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()

        presenter = extractPresenter()
        presenter.attach(this)
    }

    override fun onDestroy() {
        presenter.detach()
        _binding = null
        super.onDestroy()
    }

    override fun onRetainCustomNonConfigurationInstance(): UsersContract.Presenter {
        return presenter
    }
    private fun  extractPresenter() : UsersContract.Presenter{
        return  lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.usersRepository)
    }
    private fun initView() {
        showProgress(false)
        initRecyclerView()

        binding.refreshButton.setOnClickListener {
            presenter.onRefreshData()
        }
    }

    override fun showUsers(users: List<UserEntityDTO>) {
        adapter.setData(users)
    }

    override fun showError(throwable: Throwable) {
        toast(throwable.message)
    }

    override fun showProgress(isProgress: Boolean) {
        binding.progressBar.isVisible = isProgress
        binding.usersRecyclerView.isVisible = !isProgress
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }
}