package com.example.cryptowatcher.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptowatcher.R
import com.example.cryptowatcher.adapter.CryptoAdapter
import com.example.cryptowatcher.model.CryptoModel
import com.example.cryptowatcher.service.CryptoAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,CryptoAdapter.Listener {

    //private val BASE_URL ="https://raw.githubusercontent.com/"
    private val BASE_URL ="https://gist.githubusercontent.com/"
    private var cryptoModels:List<CryptoModel>?=null

    private lateinit var cryptoAdapter:CryptoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Base
        //https://raw.githubusercontent.com/

        //atilsamancioglu/K21-JSONDataSet/master/crypto.json

        //RecyclerView
        var layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = layoutManager


       startRetrofit()

    }



    private fun startRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object:Callback<List<CryptoModel>>{
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = it

                        cryptoModels?.let {
                            cryptoAdapter = CryptoAdapter(it,this@MainActivity)
                            findViewById<RecyclerView>(R.id.recyclerView).adapter = cryptoAdapter
                        }


                        /* for (crypto:CryptoModel in cryptoModel!!){
                             println("currency:${crypto.currency}  price:${crypto.price}")
                         }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        println("crypto:$cryptoModel")
    }
}