package com.example.app.Adapter

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.app.Image.Video
import com.example.app.Portfolio.Image
import com.example.app.R
import com.example.app.databinding.PortfolioVideoItemBinding

class VideoAdapter(private val context: Context): RecyclerView.Adapter<VideoAdapter.ViewHolder>() {
    private lateinit var viewPagerBinding: PortfolioVideoItemBinding
    var itemList = mutableListOf<Video>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewPagerBinding = PortfolioVideoItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(viewPagerBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(binding: PortfolioVideoItemBinding): RecyclerView.ViewHolder(binding.root)  {
        private val portfolioItem: VideoView = binding.modelPortfolioItem

//        init {
//            itemView.setOnClickListener {
//                itemView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
//            }
//        }

        fun bind(item: Video) {
            portfolioItem.setVideoURI(Uri.parse(item.url))
            portfolioItem.setOnCompletionListener {
                portfolioItem.start()
            }
            portfolioItem.start()
        }

        fun unbind() {

        }
    }
}