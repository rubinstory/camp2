package com.example.app.Contract

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Bitmap
import java.io.ByteArrayOutputStream


class ContractViewModel (private val repository: ContractRepository): ViewModel() {
    var contractRepository: ContractRepository = ContractRepository()
    var contractList: MutableLiveData<List<Contract>> = MutableLiveData<List<Contract>>()

    fun makeNewContract(newContract: Contract) {
        viewModelScope.launch {
            contractRepository.contract(newContract).enqueue(object: Callback<Contract> {
                override fun onResponse(
                    call: Call<Contract>,
                    response: Response<Contract>) {
                    if (response.isSuccessful){
                    }
                }
                override fun onFailure(call: Call<Contract>, t: Throwable) {
                }

            })
        }
    }

    fun getContracts(){
        viewModelScope.launch{
            contractRepository.getContracts().enqueue(object : Callback<List<Contract>> {
                override fun onResponse(
                    call: Call<List<Contract>>,
                    response: Response<List<Contract>>
                ) {
                    if (response.isSuccessful) {
                        contractList.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Contract>>, t: Throwable) {
                    Log.e("CONTRACT REPOSITORY", t.message.toString())
                }
            })
        }
    }


}
