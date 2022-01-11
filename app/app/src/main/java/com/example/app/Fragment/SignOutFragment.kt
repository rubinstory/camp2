package com.example.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RestrictTo
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.app.Authentication.Authentication
import com.example.app.Authentication.AuthenticationRepository
import com.example.app.Authentication.AuthenticationViewModel
import com.example.app.Authentication.AuthenticationViewModelFactory
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.RetrofitInstance
import com.example.app.User.UserRepository
import com.example.app.User.UserViewModel
import com.example.app.User.UserViewModelFactory
import com.example.app.databinding.SignOutFragmentBinding
import kotlinx.coroutines.*

class SignOutFragment: Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var viewModel: AuthenticationViewModel

    private var _binding: SignOutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignOutFragmentBinding.inflate(inflater, container, false)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        setSignOutBtn()
        setGetCurrentUser()
        setShowContractBtn()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSignOutBtn()
    }

    override fun onResume() {
        super.onResume()
        setSignOutBtn()
    }

    fun setGetCurrentUser() {
        var repository = UserRepository()
        val userFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this, userFactory).get(UserViewModel::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            userViewModel.getUserById(RetrofitInstance.USER_ID)
            userViewModel.user.observe(viewLifecycleOwner, Observer { user ->
                binding.signOutUserName.text = user.username
                Glide.with(requireContext()).load(user.profile_image).into(binding.profileCircleView.modelProfileImg)
            })
        }
    }

    fun setShowContractBtn() {
        binding.contractShowBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, ContractFragment())
                .commitAllowingStateLoss()
        }
    }

    fun setSignOutBtn() {
        binding.signOutBtn.setOnClickListener {
            var repository = AuthenticationRepository()
            val authenticationViewModelFactory = AuthenticationViewModelFactory(repository)
            viewModel = ViewModelProvider(this, authenticationViewModelFactory).get(AuthenticationViewModel::class.java)
            viewModel.logout()

            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, SignInFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}