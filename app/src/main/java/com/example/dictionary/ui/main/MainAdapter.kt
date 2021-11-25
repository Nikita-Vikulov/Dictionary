package com.example.dictionary.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.history.databinding.ItemWordBinding
import com.example.history.ui.DataModelCallback
import com.example.models.DataModel

class MainAdapter(
    private val itemClickListener: (DataModel) -> Unit
) : ListAdapter<DataModel, MainAdapter.MainViewHolder>(DataModelCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        ItemWordBinding.inflate(parent.inflater(), parent, false).root
    ) {

        fun bind(data: DataModel) {
            val binder = ItemWordBinding.bind(itemView)

            binder.headerTextviewRecyclerItem.text = data.text
            binder.descriptionTextviewRecyclerItem.text =
                data.meaning?.firstOrNull()?.translation?.translation

            binder.root.setOnClickListener { itemClickListener(data) }
        }
    }

    private fun ViewGroup.inflater() = LayoutInflater.from(context)
}