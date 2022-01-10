package com.example.app.User

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel(private val repository: UserRepository): ViewModel() {
    var userRepository: UserRepository = UserRepository()
    var userList: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    var user: MutableLiveData<User> = MutableLiveData<User>()


    fun getUsers(){
        viewModelScope.launch{
            userRepository.getUsers().enqueue(object: Callback<List<User>> {
                override fun onResponse(
                    call: Call<List<User>>,
                    response: Response<List<User>>) {

                    if (response.isSuccessful){
                        userList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    Log.e("USER", t.message.toString())
                }

            })

        }
    }

    fun getUserById(id : Int){
        viewModelScope.launch{
            userRepository.getUserById(id).enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.e("USER REPOSITORY", t.message.toString())
                }


            })
        }
    }

}