package com.aulmrd.github.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aulmrd.github.R
import com.aulmrd.github.data.User
import com.aulmrd.github.databinding.FragmentFollowBinding
import com.aulmrd.github.ui.UserAdapter

class FollowersFragment: Fragment (R.layout.fragment_follow), UserAdapter.OnItemClickCallback {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?. getString(DetailUserActivity.EXTRA_USERNAME).toString()

        _binding = FragmentFollowBinding.bind(view)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(activity)
            rvUser.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowersViewModel::class.java)
        viewModel.setListFollowers(username)
        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
                adapter.setOnItemClickCallback(this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }
        else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onItemClicked(data: User) {
        val intent = Intent(requireActivity(), DetailUserActivity::class.java)
        intent.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
        intent.putExtra(DetailUserActivity.EXTRA_ID, data.id)
        intent.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
        startActivity(intent)
        }
    }
