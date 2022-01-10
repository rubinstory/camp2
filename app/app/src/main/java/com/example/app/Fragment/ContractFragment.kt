package com.example.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.app.Adapter.ContractAdapter
import com.example.app.Adapter.InfluencerAdapter
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.databinding.ContractFragmentBinding

class ContractFragment: Fragment() {
    private var _binding: ContractFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var contractAdapter: ContractAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ContractFragmentBinding.inflate(inflater, container, false)

        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.black)

        setAnimation()
        contractAdapter = ContractAdapter(this.requireContext())
        loadInfluencers()
        setViewPagerAnimation()

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
        var animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation.duration = 1000

        binding.modelProfileViewpager.startAnimation(animation)
        titleAndDescriptionAnimationSlideFromBottom()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var controller = MediaController(context)
        controller.visibility = MediaController.GONE

    }

    fun setViewPagerAnimation() {
        binding.viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (lastPosition < position) {
                    titleAndDescriptionAnimationSlideFromBottom()
                }
                else if (lastPosition > position) {
                    titleAndDescriptionAnimationSlideFromTop()
                }
                else { }
                lastPosition = position
            }
        })
    }

    fun titleAndDescriptionAnimationFadeIn() {
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation2.duration = 850
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation3.duration = 1000

        binding.titleTextView.startAnimation(animation2)
        binding.descriptionTextView.startAnimation(animation3)
    }


    fun titleAndDescriptionAnimationSlideFromBottom() {
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
        animation2.duration = 850
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom)
        animation3.duration = 1000

        binding.titleTextView.startAnimation(animation2)
        binding.descriptionTextView.startAnimation(animation3)
    }

    fun titleAndDescriptionAnimationSlideFromTop() {
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.slide_in_top)
        animation2.duration = 1000
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.slide_in_top)
        animation3.duration = 850

        binding.titleTextView.startAnimation(animation2)
        binding.descriptionTextView.startAnimation(animation3)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}