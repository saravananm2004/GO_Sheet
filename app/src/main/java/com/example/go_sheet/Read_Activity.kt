package com.example.go_sheet

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class Read_Activity : AppCompatActivity() {
    lateinit var readrelativelayout:RelativeLayout

    lateinit var readProgressbar: ProgressBar
    lateinit var recycleview: RecyclerView
                  lateinit var  layoutManager: LinearLayoutManager
                           lateinit var  recyclerAdapter: ReadRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_read)
        readrelativelayout=findViewById(R.id.readrelativelayout)
        readProgressbar = findViewById(R.id.readprogressbar)
        recycleview = findViewById(R.id.recycleview)
        layoutManager=LinearLayoutManager(this)

    val productList= arrayListOf<product_list>()
      //  readrelativelayout.visibility=View.GONE
    readProgressbar.visibility=View.GONE
        val queue=Volley.newRequestQueue(this)
        val url="https://script.google.com/macros/s/AKfycbzvaieu-xfcWf4FQJ8iv35ww5KL8RiMqf6DDtXvBaSgekoEynAQdIkcgrNmxZD5mAPa5w/exec"
        val jsonObjectRequest= object:JsonObjectRequest(
        Request.Method.GET,url,null,
        Response.Listener {
            readrelativelayout.visibility=View.GONE
            readProgressbar.visibility=View.GONE
            val data =it.getJSONArray("items")
            for( i in 0 until data.length()){
                val productJSONObject=data.getJSONObject(i)//
                val productObject= product_list(
                productJSONObject.getString("ordername"),
               productJSONObject.getString("price" )
            )
               productList.add(productObject)}

             recyclerAdapter=ReadRecyclerAdapter(this,productList)
            recycleview.adapter=recyclerAdapter
            recycleview.layoutManager=layoutManager
        },

            Response.ErrorListener {
                readrelativelayout.visibility=View.GONE
            readProgressbar.visibility=View.GONE
                   Toast.makeText(this@Read_Activity,it.toString(),Toast.LENGTH_SHORT).show()

            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }

         }
        queue.add(jsonObjectRequest)
    }

}
