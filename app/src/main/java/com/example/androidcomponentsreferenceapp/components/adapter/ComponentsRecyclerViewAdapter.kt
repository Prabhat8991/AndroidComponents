package com.example.androidcomponentsreferenceapp.components.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcomponentsreferenceapp.databinding.ComponentListItemLayoutBinding

class ComponentsRecyclerViewAdapter(val listOfComponents: List<String>, val onItemClickListener: OnItemClickListener):
    RecyclerView.Adapter<ComponentsRecyclerViewAdapter.TextViewHolder>() {


    override fun getItemCount(): Int = listOfComponents.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
       return TextViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(listOfComponents[position], onItemClickListener)
    }


 class TextViewHolder private constructor(val binding: ComponentListItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

     fun bind(componentName: String, onItemClickListener: OnItemClickListener) {
         binding.componentName = componentName
         binding.onClickListener = onItemClickListener
     }

     companion object {
         fun from(parent: ViewGroup): TextViewHolder {
             val binding = ComponentListItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
             return TextViewHolder(binding)
         }
     }
 }

}
//Data binding in item layout directly
class OnItemClickListener(val clickListener: (componentName: String) -> Unit) {
    fun onClick(componentName: String) = clickListener(componentName)
}