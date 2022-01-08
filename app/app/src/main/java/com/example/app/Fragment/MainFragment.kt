package com.example.app.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.MediaController
import android.widget.TextView
import com.example.app.R
import com.example.app.Video.VideoItem
import com.example.app.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    var videoList = mutableListOf<VideoItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        binding.descriptionTextView.visibility = TextView.INVISIBLE
//        binding.modelProfileItem.modelProfileItem.visibility = ConstraintLayout.INVISIBLE
        var animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                binding.descriptionTextView.startAnimation(AnimationUtils.loadAnimation(context,
                    R.anim.fade_in
                ))
                binding.descriptionTextView.visibility = TextView.VISIBLE
//                binding.modelProfileItem.modelProfileItem.visibility = ConstraintLayout.VISIBLE
//                binding.modelProfileItem.modelProfileItem.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
            }
            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.titleTextView.startAnimation(animation)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var controller = MediaController(context)
        controller.visibility = MediaController.GONE

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}