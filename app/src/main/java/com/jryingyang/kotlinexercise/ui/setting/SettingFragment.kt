package com.jryingyang.kotlinexercise.ui.setting

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.jryingyang.kotlinexercise.BuildConfig
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.databinding.FragmentSettingBinding
import com.jryingyang.kotlinexercise.model.ResponseLogin
import com.jryingyang.kotlinexercise.network.Status
import com.jryingyang.kotlinexercise.ui.base.BaseFragment
import com.jryingyang.kotlinexercise.ui.traffic.TrafficInfoActivity

class SettingFragment : BaseFragment<FragmentSettingBinding>() {

    private lateinit var viewModel: SettingViewModel

    override fun getViewBinding(
        inflater: LayoutInflater?,
        viewGroup: ViewGroup?
    ): FragmentSettingBinding {
        return FragmentSettingBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, SettingViewModelFactory(requireContext()))
            .get(SettingViewModel::class.java)
        setHasOptionsMenu(true)
        (activity as TrafficInfoActivity).supportActionBar?.title =
            getString(R.string.setting_fragment_title)

        val loginData = arguments?.getParcelable<ResponseLogin>("loginData")

        val selectedTimeZone = viewModel.getTimeZone()?: loginData?.timezone
        binding.tvTimeZone.setText("$selectedTimeZone")

        initTimezoneData()

        viewModel.selectTimeZone.observe(viewLifecycleOwner, {
            binding.btnSave.isEnabled = it != null
        })
        binding.tvEmail.text = getString(R.string.email, loginData?.reportEmail)

        binding.btnSave.setOnClickListener {
            showLoadingDialog()
            viewModel.updateTimeZone(
                BuildConfig.X_PARSER_APPLICATION_ID,
                loginData!!.sessionToken,
                loginData.objectId,
                viewModel.selectTimeZone.value ?: 0
            ).observe(viewLifecycleOwner, {
                dismissLoadingDialog()
                when (it.status) {
                    Status.SUCCESS -> {
                        showAlertDialog(
                            getString(R.string.title_update_succes),
                            getString(R.string.content_update_succes)
                        )
                    }
                    Status.ERROR -> {
                        showAlertDialog(getString(R.string.error), it.message)
                    }
                }
            })
        }
    }

    private fun initTimezoneData() {
        val items = mutableListOf<Int>()
        for (i in -12..12) {
            items.add(i)
        }

        val adapter = ArrayAdapter(requireContext(), R.layout.list_timezone, items)
        binding.tvTimeZone.setAdapter(adapter)

        binding.tvTimeZone.setOnItemClickListener { parent, _, position, _ ->
            val item = parent.getItemAtPosition(position)
            viewModel.selectTimeZone(item as Int)
        }
    }

    private fun showAlertDialog(title: String, content: String?) {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(content)
            .setPositiveButton(getString(R.string.confirm), null)
            .show()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }
}