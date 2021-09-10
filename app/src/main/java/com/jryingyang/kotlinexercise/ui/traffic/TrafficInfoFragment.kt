package com.jryingyang.kotlinexercise.ui.traffic

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.databinding.FragmentTrafficInfoBinding
import com.jryingyang.kotlinexercise.network.Status
import com.jryingyang.kotlinexercise.ui.base.BaseFragment

class TrafficInfoFragment : BaseFragment<FragmentTrafficInfoBinding>(),
    TrafficItemAdapter.ItemClickListener {

    private lateinit var viewModel: TrafficInfoViewModel
    private lateinit var adapter: TrafficItemAdapter

    override fun getViewBinding(
        inflater: LayoutInflater?,
        viewGroup: ViewGroup?
    ): FragmentTrafficInfoBinding {
        return FragmentTrafficInfoBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, TrafficViewModelFactory())
            .get(TrafficInfoViewModel::class.java)
        (activity as TrafficInfoActivity).supportActionBar?.title =
            getString(R.string.traffic_fragment_title)

        adapter = TrafficItemAdapter(this)
        binding.rvTrafficList.layoutManager = LinearLayoutManager(context)
        binding.rvTrafficList.adapter = adapter

        updateList()

        binding.swipeLayout.setOnRefreshListener {
            updateList()
        }
    }

    private fun updateList() {
        showLoadingDialog()

        viewModel.getTrafficList().observe(viewLifecycleOwner, {
            binding.swipeLayout.isRefreshing = false
            dismissLoadingDialog()

            when (it.status) {
                Status.SUCCESS -> {
                    binding.tvUpdateTime.text =
                        getString(R.string.traffic_last_update, it.data?.updateTime)
                    if (it.data != null) {
                        adapter.submitList(it.data.News)
                    }
                }
                Status.ERROR -> {
                    showFailedDialog(it.message)
                }
            }
        })
    }

    private fun showFailedDialog(errorString: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(errorString)
            .setPositiveButton(getString(R.string.confirm), null)
            .show()
    }

    override fun onItemClicked(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}