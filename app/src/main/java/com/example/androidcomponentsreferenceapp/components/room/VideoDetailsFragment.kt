package com.example.androidcomponentsreferenceapp.components.room

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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.components.mvvm.viewmodel.MarPropertyViewModelFactory
import com.example.androidcomponentsreferenceapp.databinding.FragmentVideoGalleryBinding
import kotlinx.coroutines.channels.Channel


class VideoDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVideoGalleryBinding.inflate(inflater)
        val viewModelFactory = VideoViewModel.Factory(requireActivity().application)
        binding.progressBar.isVisible = true
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(VideoViewModel::class.java)
        binding.videoDetailsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        viewModel.playList.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            binding.videoDetailsRecyclerView.adapter = VideoDetailAdapter(it)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }
}