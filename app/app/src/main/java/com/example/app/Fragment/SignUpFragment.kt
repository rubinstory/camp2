package com.example.app.Fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app.MainActivity
import com.example.app.R
import com.example.app.Register.Register
import com.example.app.Register.RegisterRepository
import com.example.app.Register.RegisterViewModel
import com.example.app.Register.RegisterViewModelFactory
import com.example.app.RetrofitInstance
import com.example.app.databinding.SignUpFragmentBinding
import com.example.madcamp_1st_week.UserAddDialog

class SignUpFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel

    private var name: String = ""
    private var email: String = ""
    private var _binding: SignUpFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        if (arguments?.isEmpty == false) {
            name = arguments?.getString("name")!!
            email = arguments?.getString("email")!!
        }
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
        setView()
        initSignUpButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setView()

    }

    fun initSignUpButton() {
        binding.signUpBtn.setOnClickListener {
            (context as MainActivity).currentFocus?.let { view ->
                val inputMethodManager = (context as MainActivity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
            signUp()
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragment, SignInFragment())
                .commitAllowingStateLoss()
            val dialog = UserAddDialog(this.requireContext())
            dialog.setOnOKClickedListener {

            }
            dialog.start("dfs")
        }
    }


    fun setView() {
        binding.nameInputField.setText(name)
        binding.emailInputField.setText(email)
    }

    fun signUp() {
        var newUser = Register()
        val repository = RegisterRepository()
        val registerViewModelFactory = RegisterViewModelFactory(repository)
        viewModel = ViewModelProvider(this, registerViewModelFactory).get(RegisterViewModel::class.java)
        newUser.username = binding.idInputField.text.toString()
        newUser.email = binding.emailInputField.text.toString()
        newUser.password1 = binding.pwInputField.text.toString()
        newUser.password2 = binding.pwCheckInputField.text.toString()
        viewModel.registerNewUser(newUser)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setDataToView() {

    }
}