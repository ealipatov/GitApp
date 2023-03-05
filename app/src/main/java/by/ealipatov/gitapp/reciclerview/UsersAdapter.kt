package by.ealipatov.gitapp.reciclerview

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.ealipatov.gitapp.UserEntityDTO

class UsersAdapter: RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserEntityDTO>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int) = getItem(position).id.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(parent)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem (position: Int) = data[position]

    @SuppressLint("NotifyDataSetChanged")
    fun setData(users: List<UserEntityDTO>){
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }
}