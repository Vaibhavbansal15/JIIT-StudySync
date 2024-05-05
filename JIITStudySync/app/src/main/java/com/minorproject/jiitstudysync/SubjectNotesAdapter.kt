package com.minorproject.jiitstudysync

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.minorproject.jiitstudysync.databinding.CustomSubjectNotesItemBinding

class SubjectNotesAdapter(private val listener : PdfClickListener) : ListAdapter<NotesFile, SubjectNotesAdapter.SubjectNotesViewHolder>(NotesDiffCallback()) {
    inner class SubjectNotesViewHolder(private val binding : CustomSubjectNotesItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init{
            binding.root.setOnClickListener {
                listener.onPdfClicked(getItem(adapterPosition))
            }
        }

        fun bind(data : NotesFile){
            binding.notesFileName.text = data.fileName
        }

    }

    class NotesDiffCallback : DiffUtil.ItemCallback<NotesFile>() {
        override fun areItemsTheSame(oldItem: NotesFile, newItem: NotesFile): Boolean = oldItem.downloadUrl == newItem.downloadUrl

        override fun areContentsTheSame(oldItem: NotesFile, newItem: NotesFile): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectNotesViewHolder {
        val binding = CustomSubjectNotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectNotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectNotesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

interface PdfClickListener {
    fun onPdfClicked(notesFile: NotesFile)
}
