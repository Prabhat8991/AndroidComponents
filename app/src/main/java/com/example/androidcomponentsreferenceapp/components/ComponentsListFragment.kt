package com.example.androidcomponentsreferenceapp.components

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.components.adapter.ComponentsRecyclerViewAdapter
import com.example.androidcomponentsreferenceapp.components.adapter.OnItemClickListener
import com.example.androidcomponentsreferenceapp.databinding.FragmentComponentsListBinding

class ComponentsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentComponentsListBinding.inflate(inflater)
        binding.componentList.adapter = ComponentsRecyclerViewAdapter(listOfComponents = listOf<String>( "Navigation Component", "Mvvm", "Room", "Notifications", "Work Manager"), onItemClickListener = OnItemClickListener {
            when(it) {
                "Navigation Component" -> {
                   findNavController().navigate(R.id.action_componentsListFragment_to_fragmentA)
                }

                "Mvvm" -> {
                    findNavController().navigate(R.id.action_componentsListFragment_to_marsPropertyListFragment)
                }

                "Room" -> {
                    findNavController().navigate(R.id.action_componentsListFragment_to_videoDetailsFragment)
                }
            }
        })
        return binding.root
    }
}