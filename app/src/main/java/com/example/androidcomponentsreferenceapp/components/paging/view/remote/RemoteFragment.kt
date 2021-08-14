package com.example.androidcomponentsreferenceapp.components.paging.view.remote

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidcomponentsreferenceapp.R
import com.example.androidcomponentsreferenceapp.components.paging.adapter.RemoteDoggoImageAdapter
import com.example.androidcomponentsreferenceapp.databinding.FragmentRemoteBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RemoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalPagingApi
class RemoteFragment : Fragment() {

    private lateinit var binding: FragmentRemoteBinding
    private lateinit var remoteViewModel: RemoteViewModel
    private lateinit var remoteDoggoImageAdapter: RemoteDoggoImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRemoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        remoteViewModel = defaultViewModelProviderFactory.create(RemoteViewModel::class.java)
        remoteDoggoImageAdapter = RemoteDoggoImageAdapter()
        binding.rvDoggoRemote.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = remoteDoggoImageAdapter
        }
        fetchDoggoImages()
    }

    private fun fetchDoggoImages() {
        lifecycleScope.launch {
            remoteViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                remoteDoggoImageAdapter.submitData(it)
            }
        }
    }
}