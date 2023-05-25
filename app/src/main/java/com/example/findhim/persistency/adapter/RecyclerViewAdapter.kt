package com.example.findhim.persistency.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.findhim.R
import com.example.findhim.persistency.Game

class RecyclerViewAdapter(private val GameList: List<Game>): RecyclerView.Adapter<RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(layoutInflater.inflate(R.layout.log_element_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return GameList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int){
        val item = GameList[position]
        holder.render(item)
    }
}