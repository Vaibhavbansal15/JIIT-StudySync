package com.minorproject.jiitstudysync

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// Adapter class
class SubjectsAdapter(private val subjectList : ArrayList<SubjectDetails>) : RecyclerView.Adapter<SubjectsAdapter.SubjectsViewHolder>() {


    private lateinit var mListener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_subject_list,
            parent, false)
        return SubjectsViewHolder(itemView, mListener)
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        val currItem = subjectList[position]

        holder.sName.text = currItem.name
        holder.sCode.text = currItem.code
    }


    // View Holder Class
    class SubjectsViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val sName : TextView  = itemView.findViewById(R.id.sub_name)
        val sCode : TextView  = itemView.findViewById(R.id.sub_code)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}