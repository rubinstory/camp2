package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.databinding.ModelDetailFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelDetailFragment : Fragment() {
    private lateinit var viewModel: InfluencerViewModel
    private lateinit var videoAdapter: VideoAdapter

    lateinit var influencer: Influencer
    private var _binding: ModelDetailFragmentBinding? = null
    private val binding get() = _binding!!

    var videoList = mutableListOf<VideoItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModelDetailFragmentBinding.inflate(inflater, container, false)
        initViewModel()
        setFollowButton()
        setVideoAdapter()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        videoAdapter = VideoAdapter(this.requireContext())
        videoAdapter.itemList = videoList
        binding.modelDetailViewpager.adapter = videoAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoList.clear()
        _binding = null
    }

    fun setVideoAdapter() {
        for(i in 0..10) {
//            videoList.add(VideoItem("http://www.antenna.co.kr/upload/keyVisual/2015121711353535351ba.mp4"))
//            videoList.add(VideoItem("https://assets.mixkit.co/videos/preview/mixkit-girl-in-neon-sign-1232-large.mp4"))
        }
        videoList.add(VideoItem("https://assets.mixkit.co/videos/preview/mixkit-girl-in-neon-sign-1232-large.mp4"))
        videoList.add(VideoItem("https://assets.mixkit.co/videos/preview/mixkit-winter-fashion-cold-looking-woman-concept-video-39874-large.mp4"))
        videoList.add(VideoItem("https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-fashion-woman-with-silver-makeup-39875-large.mp4"))
    }


    fun setFollowButton() {
        binding.twoBtnBar.modelContractBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, LoginFragment())
                .commit()
        }
    }

    fun setDataToView() {
        binding.modelProfileView.modelDetailName.text = influencer.getFullName()
        binding.modelProfileView.modelDetailCountry.text = influencer.country
        binding.modelProfileView.modelProfileAge.text = influencer.age.toString()
        binding.modelProfileView.modelProfileHeight.text = influencer.height.toString()
        binding.modelProfileView.modelProfileWeight.text = influencer.weight.toString()
    }

    fun initViewModel() {
        val repository = InfluencerRepository()
        val influencerViewModelFactory = InfluencerViewModelFactory(repository)
        viewModel = ViewModelProvider(this, influencerViewModelFactory).get(InfluencerViewModel::class.java)
        viewModel.getInfluencerById(1)
        viewModel.influencerList.observe(viewLifecycleOwner, Observer { influencerList ->
            var influencer = influencerList[0]

            binding.modelProfileView.modelDetailName.text = influencer.getFullName()
            binding.modelProfileView.modelDetailCountry.text = influencer.country
            binding.modelProfileView.modelProfileAge.text = influencer.age.toString()
            binding.modelProfileView.modelProfileHeight.text = influencer.height.toString()
            binding.modelProfileView.modelProfileWeight.text = influencer.weight.toString()
        })

    }
//    fun loadInfluencer() {
//        var call: Call<Influencer> = RetrofitInstance.API.get_influencer_by_pk("Bearer" + " " + RetrofitInstance.token, 1)
//        call.enqueue(object: Callback<Influencer> {
//            override fun onResponse(
//                call: Call<Influencer>,
//                response: Response<Influencer>
//            ) {
//                if (response.isSuccessful) {
//                    influencer = response.body()!!
//                    setDataToView()
//                }
//                else {
//                    Log.d("RESULT", response.code().toString() + "\n" + response.errorBody().toString())
//                }
//            }
//            override fun onFailure(call: Call<Influencer>, t: Throwable) {
//                t.message?.let { Log.e("API", it) }
//            }
//        })
//    }
}