package com.example.androidcomponentsreferenceapp.components.room

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidcomponentsreferenceapp.components.room.model.VideoDomainModel
import com.example.androidcomponentsreferenceapp.databinding.VideoDetailViewBinding

class VideoDetailAdapter(val videoList: List<VideoDomainModel>): RecyclerView.Adapter<VideoDetailAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    class VideoViewHolder private constructor(val binding: VideoDetailViewBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(videoDomainModel: VideoDomainModel) {
            binding.videoDetailModel = videoDomainModel
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): VideoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VideoDetailViewBinding.inflate(layoutInflater, parent, false)
                return VideoViewHolder(binding)
            }
        }
    }

}