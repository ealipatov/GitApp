package by.ealipatov.gitapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.ealipatov.gitapp.App
import by.ealipatov.gitapp.app
import by.ealipatov.gitapp.databinding.FragmentUserListBinding
import by.ealipatov.gitapp.domain.UserEntityDTO
import by.ealipatov.gitapp.ui.navigation.OnBackPressedListener
import by.ealipatov.gitapp.utils.toast
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersListFragment : MvpAppCompatFragment(), UsersView, OnBackPressedListener {
    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun getInstance(): UsersListFragment {
            return UsersListFragment()
        }
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(app.usersRepository, App.instance.router)
    }

    private val adapter = UsersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.refreshButton.setOnClickListener {
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initRecyclerView() {
        with(binding) {
            usersRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            usersRecyclerView.adapter = adapter
        }
    }

    override fun onBackPressed() = presenter.onBackPressed()

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
}