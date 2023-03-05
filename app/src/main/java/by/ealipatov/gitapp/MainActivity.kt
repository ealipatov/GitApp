package by.ealipatov.gitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        initRecyclerView()

        binding.refreshButton.setOnClickListener{
            toast("Button pressed")
            usersRepository.getUsers(
                onSuccess = adapter::setData,
                onError = {toast(getString(R.string.error_data_loading))}
            )
        }
    }

    private fun initRecyclerView() {
        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.usersRecyclerView.adapter = adapter
    }
}