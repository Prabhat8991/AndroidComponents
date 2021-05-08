package com.example.androidcomponentsreferenceapp.components.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.databinding.FragmentABinding

class FragmentA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentABinding.inflate(inflater)
        binding.buttonFragmentA.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentA_to_fragmentB)
        }
        return binding.root
    }
}