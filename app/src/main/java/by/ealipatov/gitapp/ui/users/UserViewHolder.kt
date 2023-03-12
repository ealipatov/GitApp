package by.ealipatov.gitapp.ui.users

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.ealipatov.gitapp.R
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.databinding.ItemUserBinding
import by.ealipatov.gitapp.ui.profile.UserProfileActivity
import coil.load

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
) {


    private val binding = ItemUserBinding.bind(itemView)

    fun bind(userEntity: UserEntityDTO, position: Int) = with(itemView){
        binding.userIdTextView.text = userEntity.id.toString()
        binding.userLoginTextView.text = userEntity.login
        displayUserAvatar(userEntity.avatarUrl)
        setOnClickListener {
            val intent = Intent(context, UserProfileActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }

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