package by.ealipatov.gitapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.ealipatov.gitapp.app
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.databinding.ActivityMainBinding
import by.ealipatov.gitapp.ui.users.UsersAdapter
import by.ealipatov.gitapp.domain.UsersRepository
import by.ealipatov.gitapp.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepository: UsersRepository by lazy {app.usersRepository}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        showProgressBar(false)
        initRecyclerView()

        binding.refreshButton.setOnClickListener{
            loadData()
        }
    }

    private fun loadData(){
        showProgressBar(true)
        usersRepository.getUsers(
            onSuccess = {
                showProgressBar(false)
                onDataLoaded(it)
            },
            onError = {
                showProgressBar(false)
                onError(it)
            }
        )
    }

    private fun onDataLoaded(data: List<UserEntityDTO>){
        adapter.setData(data)
    }

    private fun onError(throwable: Throwable){
        toast(throwable.message)
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