package com.example.go_sheet

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class WriteActivity : AppCompatActivity() {
    lateinit var writerelativelayout: RelativeLayout
    lateinit var writeprogressbar: ProgressBar
    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btnwrite:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_write)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        writerelativelayout=findViewById(R.id.writerelativelayout)
        writeprogressbar= findViewById(R.id.writeprogressbar)
        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btnwrite= findViewById(R.id.btnwrite)

        writerelativelayout.visibility-View.GONE
        writeprogressbar.visibility-View.GONE

        btnwrite.setOnClickListener {
            if (edt1.text.toString().isEmpty() or edt2.text.toString().isEmpty()){
                Toast.makeText(this@WriteActivity,"Enter the data",Toast.LENGTH_LONG).show()
            }
            else{
                writerelativelayout.visibility-View.VISIBLE
                writeprogressbar.visibility-View.VISIBLE
                val url="https://script.google.com/macros/s/AKfycbw7YVvhlu2YlndeLB08QtESGZ5jLhXT8820o7xLtpnJm19SqrkEc5CXVwKAheu7qDjH8g/exec"
                val stringRequest=object :StringRequest(Request.Method.POST,url,
                    Response.Listener {
                                      Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_LONG).show()
                        writerelativelayout.visibility-View.GONE
                        writeprogressbar.visibility-View.GONE
                    },
                    Response.ErrorListener {
                        Toast.makeText(this@WriteActivity,it.toString(),Toast.LENGTH_LONG).show()
                        writerelativelayout.visibility-View.GONE
                        writeprogressbar.visibility-View.GONE
                    }){
                    override fun getParams(): MutableMap<String, String> {
                        val params=HashMap<String,String>()
                        params["orderName"]=edt1.text.toString()
                        params["price"]=edt2.text.toString()
                        return params
                    }


                }
                val queue:RequestQueue=Volley.newRequestQueue(this@WriteActivity)
                queue.add(stringRequest)
            }

        }
    }
}