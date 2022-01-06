package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.databinding.ModelDetailFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelDetailFragment : Fragment() {
    lateinit var myInfluencer: InfluencerItem
    private var _binding: ModelDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ModelDetailFragmentBinding.inflate(inflater, container, false)
        loadInfluencer()
        setFollowButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setFollowButton() {
        binding.twoBtnBar.modelContractBtn.setOnClickListener({
            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, LoginFragment())
                .commit()
        })
    }

    fun setDataToView() {
        binding.modelProfileView.modelDetailName.text = myInfluencer.full_name
        binding.modelProfileView.modelDetailCountry.text = myInfluencer.country
    }
    
    fun loadInfluencer() {
        var call: Call<InfluencerItem> = DjangoAPICall.API.get_influencer_by_pk(1)
        call.enqueue(object: Callback<InfluencerItem> {
            override fun onResponse(
                call: Call<InfluencerItem>,
                response: Response<InfluencerItem>
            ) {
                if (response.isSuccessful) {
                    myInfluencer = response.body()!!
                    setDataToView()
                }
            }
            override fun onFailure(call: Call<InfluencerItem>, t: Throwable) {
                t.message?.let { Log.e("API", it) }
            }
        })
    }
}