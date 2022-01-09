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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.app.Adapter.InfluencerAdapter
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.Video.VideoItem
import com.example.app.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var influencerAdapter: InfluencerAdapter
    private lateinit var viewModel: InfluencerViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainFragmentBinding.inflate(inflater, container, false)

        setAnimation()
        influencerAdapter = InfluencerAdapter(this.requireContext())
        loadInfluencers()

        return binding.root
    }

    fun loadInfluencers() {
        val repository = InfluencerRepository()
        val influencerViewModelFactory = InfluencerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, influencerViewModelFactory).get(InfluencerViewModel::class.java)
        viewModel.getInfluencers()
        viewModel.influencerList.observe(viewLifecycleOwner, Observer { influencerList ->
            influencerAdapter.itemList = influencerList.toMutableList()
            binding.viewpager.adapter = influencerAdapter
        })
    }

    fun setAnimation() {
        var animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation1.duration = 1000

        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation2.duration = 1500
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation3.duration = 2000

        binding.titleTextView.startAnimation(animation2)
        binding.descriptionTextView.startAnimation(animation3)
        binding.modelProfileViewpager.startAnimation(animation1)
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