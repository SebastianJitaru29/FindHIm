package com.example.findhim.persistency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.findhim.R
import com.example.findhim.persistency.adapter.RecyclerViewAdapter

class LogActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_layout)
        initRecyclerView()
    }

    fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)
        val recyclerView = findViewById<RecyclerView>(R.id.recycleSuper)

        recyclerView.layoutManager = manager
        //recyclerView.adapter = RecyclerViewAdapter()//aqui le paso una lista de partidas, la sacare de la base de datos
        recyclerView.addItemDecoration(decoration)
    }
}