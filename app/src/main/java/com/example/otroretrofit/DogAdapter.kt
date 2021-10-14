package com.example.otroretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.otroretrofit.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogAdapter(val images:List<String>):RecyclerView.Adapter<DogAdapter.dogholder>() {
    class dogholder(view: View):RecyclerView.ViewHolder(view) {
        val binding=ItemDogBinding.bind(view)
        fun bind(image:String){
            Picasso.get().load(image).into(binding.idDog)


        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dogholder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return dogholder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(holder: dogholder, position: Int) {
        val item=images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
       return images.size
    }
}