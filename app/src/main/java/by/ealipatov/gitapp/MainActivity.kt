package by.ealipatov.gitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.ealipatov.gitapp.databinding.ActivityMainBinding
import by.ealipatov.gitapp.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.refreshButton.setOnClickListener{
            toast("Button pressed")
        }
    }
}