package com.example.findhim.persistency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.findhim.R

class LogActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_layout)
        initRecyclerView()
    }

    fun initRecyclerView(){

    }
}