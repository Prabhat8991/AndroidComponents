package com.example.androidcomponentsreferenceapp.components.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcomponentsreferenceapp.components.mvvm.model.MarsPropertyDetail
import com.example.androidcomponentsreferenceapp.databinding.MarsListItemBinding

class MarsPropertiesListAdapter(val propertyList: List<MarsPropertyDetail>, val onClickListener: OnMarsPropertyClickListener):
    RecyclerView.Adapter<MarsPropertiesListAdapter.PropertyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        return PropertyViewHolder.from(parent)
    }

    override fun getItemCount(): Int = propertyList.size

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        holder.bind(propertyList[position], onClickListener)
    }

    class PropertyViewHolder private constructor(val binding: MarsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marsListItem: MarsPropertyDetail, onClickListener: OnMarsPropertyClickListener) {
            binding.marsItem = marsListItem
            binding.onClickListener = onClickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PropertyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MarsListItemBinding.inflate(layoutInflater, parent, false)
                return PropertyViewHolder(binding)
            }

        }
    }
}

class OnMarsPropertyClickListener(val onClickListener: (propertyId: String) -> Unit) {
    fun onItemClicked(marsPropertyDetail: MarsPropertyDetail) =
        onClickListener(marsPropertyDetail.id)
}