package by.ealipatov.gitapp.reciclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.ealipatov.gitapp.R
import by.ealipatov.gitapp.UserEntityDTO
import by.ealipatov.gitapp.databinding.ItemUserBinding
import coil.load

class UserViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {
    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntityDTO){
        binding.userIdTextView.text = userEntity.id.toString()
        binding.userLoginTextView.text = userEntity.login
        displayUserAvatar(userEntity.avatarUrl)
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