package com.jryingyang.kotlinexercise.ui.traffic

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jryingyang.kotlinexercise.R
import com.jryingyang.kotlinexercise.databinding.ItemTrafficBinding
import com.jryingyang.kotlinexercise.model.ResponseTrafficList

class TrafficItemAdapter(private val listener: ItemClickListener) :
    ListAdapter<ResponseTrafficList.Traffic, TrafficItemAdapter.ViewHolder>(ItemDiffCallback()) {

    class ViewHolder(private val binding: ItemTrafficBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResponseTrafficList.Traffic, listener: ItemClickListener) = with(binding) {
            tvTitle.text = item.chtmessage
            tvStartTime.text = root.context.getString(R.string.start_time, item.starttime)
            tvEndTime.text = root.context.getString(R.string.end_time, item.endtime)

            if (item.content == item.chtmessage) {
                tvContent.visibility = View.GONE
            } else {
                tvContent.visibility = View.VISIBLE
                tvContent.text = item.content
            }

            if (TextUtils.isEmpty(item.url)) {
                tvDetail.visibility = View.GONE
            } else {
                tvDetail.visibility = View.VISIBLE
            }

            tvDetail.setOnClickListener {
                listener.onItemClicked(item.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTrafficBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, listener)
    }

    interface ItemClickListener {
        fun onItemClicked(url: String)
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<ResponseTrafficList.Traffic>() {

    override fun areItemsTheSame(
        oldItem: ResponseTrafficList.Traffic,
        newItem: ResponseTrafficList.Traffic
    ): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(
        oldItem: ResponseTrafficList.Traffic,
        newItem: ResponseTrafficList.Traffic
    ): Boolean {
        return oldItem == newItem
    }
}