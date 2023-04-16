package com.example.cryptowatcher.service

import com.example.cryptowatcher.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {


    //Base
    //https://raw.githubusercontent.com/
    //https://gist.githubusercontent.com/

    //atilsamancioglu/K21-JSONDataSet/master/crypto.json
   // https://gist.githubusercontent.com/
    // faikahmet/b542fbee355d6d27064d35cd2f1c459f/raw/d6b4b10c1a6d5babf260176c94fadd2b98186c7c/crypto
    @GET("faikahmet/911cc4f8f12224d1a63db1e212437c46/raw/d7ae040f699eab3b33fe3abe066a71b096ebeeca/crypto.json")
    fun getData():Call<List<CryptoModel>>


}