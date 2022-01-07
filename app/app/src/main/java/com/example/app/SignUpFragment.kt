package com.example.app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.app.databinding.LoginFragmentBinding
import com.example.app.databinding.SignupFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpFragment : Fragment() {
    private var name: String = ""
    private var email: String = ""
    private var _binding: SignupFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignupFragmentBinding.inflate(inflater, container, false)
        if (arguments?.isEmpty == false) {
            name = arguments?.getString("name")!!
            email = arguments?.getString("email")!!
        }
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
            signUp()
        }
    }


    fun setView() {
        binding.nameInputField.setText(name)
        binding.emailInputField.setText(email)
    }

    fun signUp() {
        var registerItem = RegisterItem()
        registerItem.username = binding.idInputField.text.toString()
        registerItem.email = binding.emailInputField.text.toString()
//        var name = binding.nameInputField.text
        registerItem.password1 = binding.pwInputField.text.toString()
        registerItem.password2 = binding.pwCheckInputField.text.toString()

        var call: Call<RegisterItem> = DjangoAPICall.API.register_new_user(registerItem)
        call.enqueue(object: Callback<RegisterItem> {
            override fun onResponse(call: Call<RegisterItem>, response: Response<RegisterItem>) {
                Log.d("RESULT", response.message() + "\n" + response.errorBody().toString() + "\n" + response.body().toString())
            }

            override fun onFailure(call: Call<RegisterItem>, t: Throwable) {
                Log.e("SIGNUP", t.message.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setDataToView() {

    }
}