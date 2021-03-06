package com.jryingyang.kotlinexercise.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.jryingyang.kotlinexercise.R

abstract class BaseAppCompatActivity<T : ViewBinding> : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!

    private val loadingDialog:LoadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()

        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        dismissLoadingDialog()
    }

    fun showLoadingDialog() {
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
        }
    }

    fun dismissLoadingDialog() {
        if (loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    protected abstract fun getViewBinding(): T

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fadein_right, R.anim.fadeout_right)
    }
}