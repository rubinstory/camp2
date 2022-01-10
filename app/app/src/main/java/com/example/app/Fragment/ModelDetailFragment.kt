package com.example.app.Fragment

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.app.Adapter.ImageAdapter
import com.example.app.Adapter.VideoAdapter
import com.example.app.Contract.Contract
import com.example.app.Contract.ContractRepository
import com.example.app.Contract.ContractViewModel
import com.example.app.Contract.ContractViewModelFactory
import com.example.app.Influencer.Influencer
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.R
import com.example.app.RetrofitInstance
import com.example.app.databinding.ModelDetailFragmentBinding
import com.github.gcacace.signaturepad.views.SignaturePad
import com.google.android.material.tabs.TabLayout
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import android.os.Environment
import java.io.*
import android.provider.MediaStore

import android.content.ContentUris
import android.content.ContentValues.TAG
import android.content.Context
import android.database.Cursor
import android.util.Log
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Header
import android.graphics.BitmapFactory





class ModelDetailFragment : Fragment() {
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var videoAdapter: VideoAdapter
    private lateinit var viewModel: InfluencerViewModel
    private lateinit var contractviewModel: ContractViewModel
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
        setContractButton()
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


    fun setContractButton() {
        binding.twoBtnBar.modelContractBtn.setOnClickListener(View.OnClickListener {

            if(RetrofitInstance.TOKENUSERID == -1){
                println("TOKENUSERID: " + RetrofitInstance.TOKENUSERID)
                println("flag3")
                requireActivity().supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.fragment, SignInFragment())
                    .commit()
            }
            else{
                binding.contractview.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED)

                setSignaturePad()
                contractDecline()
                contractAccept()
            }



        })
    }

    fun setSignaturePad(){
        binding.signaturepanel.signaturePad.setOnSignedListener(object: SignaturePad.OnSignedListener {
            override fun onStartSigning() {
                binding.signaturepanel.signatureText.visibility = View.INVISIBLE
            }

            override fun onSigned() {
                binding.signaturepanel.signatureText.visibility = View.INVISIBLE
            }

            override fun onClear() {
                binding.signaturepanel.signatureText.visibility = View.VISIBLE

            }
        })

        //touch outside of pannel
        binding.constraintlayout.setOnClickListener(View.OnClickListener {
            binding.contractview.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED)
            binding.signaturepanel.signaturePad.clear()
            binding.signaturepanel.signatureText.visibility = View.VISIBLE

        })
    }

    fun contractDecline(){
        binding.signaturepanel.contractTwoButtonBar.declineBtn.setOnClickListener(View.OnClickListener {
            binding.contractview.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED)
            binding.signaturepanel.signaturePad.clear()
            binding.signaturepanel.signatureText.visibility = View.VISIBLE
        })
    }

    fun contractAccept() {
        binding.signaturepanel.contractTwoButtonBar.accpetBtn.setOnClickListener(View.OnClickListener {
            val signaturefile: Bitmap = binding.signaturepanel.signaturePad.getSignatureBitmap()
            binding.signaturepanel.signaturePad.clear()
            binding.signaturepanel.signatureText.visibility = View.VISIBLE
            binding.contractview.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED)

            val repository = ContractRepository()
            val contractViewModelFactory = ContractViewModelFactory(repository)
            contractviewModel = ViewModelProvider(this, contractViewModelFactory).get(ContractViewModel::class.java)

            val byteArrayOutputStream = ByteArrayOutputStream()
            signaturefile.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
            val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), byteArrayOutputStream.toByteArray())
            val uploadFile = MultipartBody.Part.createFormData("signature", "something.jpeg", requestBody)

            RetrofitInstance.api.make_contract(signatrue = uploadFile, influencerId, RetrofitInstance.TOKENUSERID).enqueue(object: Callback<Contract> {
                override fun onResponse(call: Call<Contract>, response: Response<Contract>) {
                    val headers = response.headers()
                    Log.d("TAG", headers.toString())
                    Toast.makeText(requireActivity(), "거래가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<Contract>, t: Throwable) {}
            })
        })
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

