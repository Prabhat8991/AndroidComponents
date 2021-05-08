package com.example.androidcomponentsreferenceapp.components.mvvm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.androidcomponentsreferenceapp.components.mvvm.adapter.MarsPropertiesListAdapter
import com.example.androidcomponentsreferenceapp.components.mvvm.adapter.OnMarsPropertyClickListener
import com.example.androidcomponentsreferenceapp.components.mvvm.network.MarsApi
import com.example.androidcomponentsreferenceapp.components.mvvm.network.MarsApiRepository
import com.example.androidcomponentsreferenceapp.components.mvvm.viewmodel.MarPropertyViewModel
import com.example.androidcomponentsreferenceapp.components.mvvm.viewmodel.MarPropertyViewModelFactory
import com.example.androidcomponentsreferenceapp.databinding.FragmentMarsPropertyListBinding

class MarsPropertyListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMarsPropertyListBinding.inflate(inflater)
        val repository = MarsApiRepository(MarsApi.retrofitService)
        val viewModelFactory = MarPropertyViewModelFactory(repository)
        binding.progressBarMars.isVisible = true
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(MarPropertyViewModel::class.java)
        viewModel.propertyList.observe(viewLifecycleOwner, Observer {
            binding.progressBarMars.isVisible = false
            binding.propertiesRecyclerView.adapter = MarsPropertiesListAdapter(it, OnMarsPropertyClickListener {
                Toast.makeText(requireActivity(), "$it Selected", Toast.LENGTH_SHORT).show()
            })
        })
        return binding.root
    }
}