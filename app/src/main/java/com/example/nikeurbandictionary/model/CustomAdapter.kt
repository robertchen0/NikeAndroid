package com.example.nikeurbandictionary.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeurbandictionary.R
import com.example.nikeurbandictionary.model.classes.Definition

class CustomAdapter(val data: Definition) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun getItemCount(): Int = data.list.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tv_number.text = (position + 1).toString()
        holder.tv_def.text = data.list[position].definition
        holder.tv_exam.text = data.list[position].example
        holder.tv_thumbs_up.text = data.list[position].thumbs_up.toString()
        holder.tv_thumbs_down.text = data.list[position].thumbs_down.toString()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_number: TextView = itemView.findViewById(R.id.tv_number)
        val tv_def: TextView = itemView.findViewById(R.id.tv_def)
        val tv_exam: TextView = itemView.findViewById(R.id.tv_example)
        val tv_thumbs_up: TextView = itemView.findViewById(R.id.tv_thumps_up)
        val tv_thumbs_down: TextView = itemView.findViewById(R.id.tv_thumps_down)
    }
}