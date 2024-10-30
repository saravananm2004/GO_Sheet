package com.example.go_sheet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btr:Button
    lateinit var btw:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btr = findViewById(R.id.btnread)
        btw = findViewById(R.id.btnwrite)

        btr.setOnClickListener{
            val intent=Intent(this@MainActivity,Read_Activity::class.java)
            startActivity(intent)
        }
        btw.setOnClickListener{
            val intent=Intent(this@MainActivity,WriteActivity::class.java)
            startActivity(intent)



        }
    }
}