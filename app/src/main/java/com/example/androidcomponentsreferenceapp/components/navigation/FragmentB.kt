package com.example.androidcomponentsreferenceapp.components.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.databinding.FragmentBBinding

class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentBBinding.inflate(inflater)
        binding.buttonFragmentC.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentB_to_fragmentC)
        }
        return binding.root
    }
}