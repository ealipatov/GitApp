package by.ealipatov.gitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.ealipatov.gitapp.databinding.ActivityMainBinding
import by.ealipatov.gitapp.reciclerview.UsersAdapter
import by.ealipatov.gitapp.repository.LocalUsersRepositoryImpl
import by.ealipatov.gitapp.repository.UsersRepository
import by.ealipatov.gitapp.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepository: UsersRepository = LocalUsersRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showProgressBar(false)
        initRecyclerView()

        binding.refreshButton.setOnClickListener{
            toast("Button pressed")
            showProgressBar(true)
            usersRepository.getUsers(
                onSuccess = {
                    showProgressBar(false)
                    adapter.setData(it)
                },
                onError = {
                    showProgressBar(false)
                    toast(getString(R.string.error_data_loading))
                }
            )
        }
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }

    private fun showProgressBar(isProgress: Boolean){
        binding.progressBar.isVisible = isProgress
        binding.usersRecyclerView.isVisible = !isProgress
    }
}