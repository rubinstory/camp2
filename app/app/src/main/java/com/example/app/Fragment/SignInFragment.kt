package com.example.app.Fragment

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.Authentication.Authentication
import com.example.app.Authentication.AuthenticationRepository
import com.example.app.Authentication.AuthenticationViewModel
import com.example.app.Authentication.AuthenticationViewModelFactory
import com.example.app.Influencer.InfluencerRepository
import com.example.app.Influencer.InfluencerViewModel
import com.example.app.Influencer.InfluencerViewModelFactory
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.databinding.SignInFragmentBinding
import com.kakao.sdk.user.UserApiClient

class SignInFragment : Fragment() {
    private lateinit var viewModel: AuthenticationViewModel

    private var _binding: SignInFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignInFragmentBinding.inflate(inflater, container, false)
        setLoginAndSignupBtn()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun activateFadeInAnimationForEachLinearLayouts() {

        var animation1 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation1.duration = 1000
        var animation2 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation2.duration = 1500
        var animation3 = AnimationUtils.loadAnimation(context, R.anim.fade_in)
        animation3.duration = 2000
        binding.companyLogoView.startAnimation(animation1)
        binding.signInView.startAnimation(animation2)
        binding.buttonView.startAnimation(animation3)
    }

    fun setLoginAndSignupBtn() {
        activateFadeInAnimationForEachLinearLayouts()
        setLogInBtn()
        setSignUpBtn()
        binding.signUpWithKakaoBtn.setOnClickListener { setKakao() }
    }

    fun setLogInBtn() {
        binding.loginBtn.setOnClickListener {
            val repository = AuthenticationRepository()
            val authenticationViewModelFactory = AuthenticationViewModelFactory(repository)
            viewModel = ViewModelProvider(this, authenticationViewModelFactory).get(AuthenticationViewModel::class.java)
            viewModel.loginWithAccount(Authentication(binding.idInputField.text.toString(), binding.pwInputField.text.toString()))
            viewModel.HTTP_STATUS.observe(viewLifecycleOwner, Observer { status ->
                when(status) {
                    200 -> {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.fragment, MainFragment())
                            .commitAllowingStateLoss()
                    }
                    else -> {
                        binding.idInputLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
                        binding.pwInputLayout.startAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_in))
                        binding.idInputLayout.error = "Please check ID or Password"
                        binding.pwInputLayout.error = "Please check ID or Password"
                    }
                }
            })
        }
    }

    fun setSignUpBtn() {
        binding.signUpBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, SignUpFragment())
                .commitAllowingStateLoss()
        }
    }

    fun setKakao() {
        UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패", error)
            }
            else if (token != null) {
                Log.i(TAG, "로그인 성공 ${token.accessToken}")
                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보 요청 실패", error)
                    }
                    else if (user != null) {
                        Log.i(TAG, "사용자 정보 요청 성공" +
                                "\n회원번호: ${user.id}" +
                                "\n이메일: ${user.kakaoAccount?.email}" +
                                "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                                "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                        var bundle: Bundle = Bundle()
                        bundle.putString("email", user.kakaoAccount?.email)
                        bundle.putString("name", user.kakaoAccount?.profile?.nickname)

                        var signUpFragment = SignUpFragment()
                        signUpFragment.arguments = bundle

                        activity?.supportFragmentManager?.beginTransaction()
                            ?.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            ?.replace(R.id.fragment, signUpFragment)
                            ?.commitAllowingStateLoss()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}