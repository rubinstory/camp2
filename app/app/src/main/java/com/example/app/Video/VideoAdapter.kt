package com.example.app.Video

import com.example.app.databinding.VideoItemViewBinding

import android.content.Context
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.*
import com.example.app.R
import org.json.JSONArray


class VideoAdapter (private val context: Context): RecyclerView.Adapter<VideoAdapter.ViewHolder>(){
    private lateinit var videoViewBinding: VideoItemViewBinding
    var itemList = mutableListOf<VideoItem>()
    private lateinit var jsonArray: JSONArray
    private lateinit var deleteBtn : ImageButton

    init {  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        videoViewBinding = VideoItemViewBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = LayoutInflater.from(context).inflate(R.layout.video_item_view,parent,false)
        return ViewHolder(videoViewBinding)
    }

    override fun getItemCount(): Int { return itemList.size }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(itemList[position]) }

    inner class ViewHolder(view: VideoItemViewBinding) : RecyclerView.ViewHolder(view.root) {

        private val videoView: VideoView = videoViewBinding.modelVideoItem

//        @SuppressLint("ResourceAsColor")
        fun bind(item: VideoItem) {
            var controller = MediaController(context)
            controller.visibility = MediaController.GONE
            videoView.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp->
                mp.isLooping = true
            })
            videoView.setMediaController(controller)
            videoView.setVideoPath(item.url)
//            videoView.setVideoURI(Uri.parse(item.url))
//            videoView.start()
        }


        fun unbind(position: Int) {

        }
    }



}