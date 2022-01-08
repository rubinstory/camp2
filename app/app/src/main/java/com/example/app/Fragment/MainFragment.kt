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
import androidx.constraintlayout.widget.ConstraintLayout
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

//        binding.descriptionTextView.visibility = TextView.INVISIBLE
//        binding.modelProfileItem.modelProfileItem.visibility = ConstraintLayout.INVISIBLE
        var animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_in)

        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation2.duration = 1000
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation3.duration = 2000

        binding.titleTextView.startAnimation(animation1)
        binding.descriptionTextView.startAnimation(animation2)
        binding.modelProfileItem.modelProfileItem.startAnimation(animation3)

        binding.modelProfileItem.modelProfileItem.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, ModelDetailFragment())
                .commit()
        }

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