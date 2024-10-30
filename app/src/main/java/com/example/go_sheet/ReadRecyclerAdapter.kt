package com.example.go_sheet

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ReadRecyclerAdapter(val context:Context,val productList:ArrayList<product_list>)

    :RecyclerView.Adapter<ReadRecyclerAdapter.ReadViewHolder>(){
    class ReadViewHolder(view : View):RecyclerView
    .ViewHolder(view){
        val ordername :TextView=view.findViewById(R.id.orderName)
        val price :TextView=view.findViewById(R.id.Price)
        //val ordername :TextureView=view.findViewById(R.id.OrdeName)
        //val  price :TextureView=view.findViewById(R.id.Price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReadViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.single_product_item,parent,false)
        return ReadViewHolder(view)

    }

    override fun getItemCount(): Int {
        return productList.size

    }

    override fun onBindViewHolder(holder: ReadViewHolder, position: Int) {
        holder.ordername.text=productList[position].ordername
        holder.price.text=productList[position].price

    }
}