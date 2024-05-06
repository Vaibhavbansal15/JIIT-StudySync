package com.minorproject.jiitstudysync

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SubjectPyqAdapter(private val context: Context, private val listImages : ArrayList<PyqFile>) : RecyclerView.Adapter<SubjectPyqAdapter.SubjectPyqViewHolder>(){

    class SubjectPyqViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val imageView : ImageView = itemView.findViewById(R.id.pyqIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectPyqViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_subject_pyq_item, parent, false)
        return SubjectPyqViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubjectPyqViewHolder, position: Int) {
        Glide.with(context).load(listImages[position].url).into(holder.imageView)
        holder.itemView.findViewById<TextView>(R.id.pyqFileName).setText(listImages[position].year+"_"+listImages[position].exam)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }
}