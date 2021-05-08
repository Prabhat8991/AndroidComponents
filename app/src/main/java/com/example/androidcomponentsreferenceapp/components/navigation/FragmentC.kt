package com.example.androidcomponentsreferenceapp.components.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.databinding.FragmentCBinding

class FragmentC : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCBinding.inflate(inflater)
        binding.buttonFragmentA.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentC_to_fragmentA2)
        }
        return binding.root
    }
}