package com.example.app.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TableLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.app.Adapter.ImageAdapter
import com.example.app.Adapter.VideoAdapter
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.databinding.ModelDetailFragmentBinding
import com.google.android.material.tabs.TabLayout

class ModelDetailFragment : Fragment() {
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var viewModel: InfluencerViewModel
    private var influencerId: Int = 1

    lateinit var influencer: Influencer
    private var _binding: ModelDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModelDetailFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        influencerId = arguments?.getInt("id")!!
        imageAdapter = ImageAdapter(this.requireContext())
        videoAdapter = VideoAdapter(this.requireContext())

        initViewModel()
        setContractBtn()
        setTabLayout()
        activateFadeInAnimationForEachLinearLayouts()
        return binding.root
    }

    fun setTabLayout() {
        binding.modelProfileTabBar.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.text) {
                    "PORTFOLIO" -> binding.modelDetailViewpager.adapter = imageAdapter
                    "VIDEO" -> binding.modelDetailViewpager.adapter = videoAdapter
                }
                var animation = AnimationUtils.loadAnimation(context, R.anim.fade_in)
                animation.duration = 1000
//                requireActivity().findViewById<ImageView>(R.id.model_portfolio_item).startAnimation(animation)
//                binding.modelDetailViewpager.startAnimation(animation)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun activateFadeInAnimationForEachLinearLayouts() {

        var animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation1.duration = 1000
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation2.duration = 1500
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation3.duration = 2000
        binding.modelProfileView.modelProfileView.startAnimation(animation1)
        binding.twoBtnBar.twoBtnBar.startAnimation(animation2)
        binding.modelProfileTabBar.startAnimation(animation3)
        binding.modelDetailViewpager.startAnimation(animation3)
    }

    fun setContractBtn() {
        binding.twoBtnBar.modelContractBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, ContractFragment())
                .commit()
        }
    }


    fun initViewModel() {
        val repository = InfluencerRepository()
        val influencerViewModelFactory = InfluencerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, influencerViewModelFactory).get(InfluencerViewModel::class.java)
        viewModel.getInfluencerById(influencerId)
        viewModel.influencerList.observe(viewLifecycleOwner, Observer { influencerList ->
            var influencer = influencerList[0]
            binding.modelProfileView.modelDetailName.text = influencer.getFullName()
            binding.modelProfileView.modelDetailCountry.text = influencer.country
            binding.modelProfileView.modelProfileAge.text = influencer.age.toString()
            binding.modelProfileView.modelProfileHeight.text = influencer.height.toString()
            binding.modelProfileView.modelProfileWeight.text = influencer.weight.toString()
            Glide.with(this).load(influencer.imageList[0].url).into(binding.modelProfileView.profileCircleView.modelProfileImg)
            imageAdapter.itemList = influencer.imageList.toMutableList()
            videoAdapter.itemList = influencer.videoList.toMutableList()
            binding.modelDetailViewpager.adapter = imageAdapter
        })

    }
}