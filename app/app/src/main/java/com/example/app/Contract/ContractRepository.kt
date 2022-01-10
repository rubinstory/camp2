package com.example.app.Contract

import com.example.app.RetrofitInstance

class ContractRepository {
//    fun contract(contract: Contract) = RetrofitInstance.api.make_contract()

    fun getContracts() = RetrofitInstance.api.get_contracts()
}