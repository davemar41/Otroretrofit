package com.example.otroretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otroretrofit.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.stream.DoubleStream.builder


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private lateinit var binding:ActivityMainBinding
    private lateinit var adapter:DogAdapter
    private  val imagedogs= mutableListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.sch.setOnQueryTextListener(this)
        init()
    }
    private fun init(){
        adapter= DogAdapter(imagedogs)
        binding.rv.layoutManager=LinearLayoutManager(this)
        binding.rv.adapter=adapter


    }
    private fun getRetrofit():Retrofit{
        return  Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
                .build()

    }
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call:Response<DogResponse> =
                getRetrofit().create(ApiService::class.java).getDogsResponse("$query/images")
            val puppies:DogResponse?=call.body()
            runOnUiThread{
                if(call.isSuccessful){
                   val images:List<String> = puppies?.images?: emptyList()
                    imagedogs.clear()
                    imagedogs.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showerror()
                }
            }

        }

    }

    private fun showerror() {
        Toast.makeText(this,"ha ocurrido un error", Toast.LENGTH_LONG).show()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(!p0.isNullOrEmpty()){
            searchByName(p0.lowercase(Locale.getDefault()))

        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }


}




