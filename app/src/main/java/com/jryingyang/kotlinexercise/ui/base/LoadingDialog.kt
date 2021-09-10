package com.jryingyang.kotlinexercise.ui.base

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.jryingyang.kotlinexercise.R

class LoadingDialog(context: Context) : Dialog(context) {
    private val layout: LinearLayout = LinearLayout(context)
    private var progressBar: ProgressBar

    init {
        layout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.gravity = Gravity.CENTER
        progressBar = ProgressBar(context)
        progressBar.indeterminateTintList = ColorStateList.valueOf(context.getColor(R.color.orange))

        layout.addView(progressBar)
        setContentView(layout)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}