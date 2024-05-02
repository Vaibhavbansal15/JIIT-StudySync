package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.minorproject.jiitstudysync.databinding.ActivitySubjectNotesBinding

class SubjectNotes : AppCompatActivity() {

    private val binding : ActivitySubjectNotesBinding by lazy{
        ActivitySubjectNotesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sName : TextView = binding.notesSubName
        val sCode : TextView = binding.notesSubCode

        val bundle : Bundle? = intent.extras
        sName.text = bundle!!.getString("notesSubName")
        sCode.text = bundle.getString("notesSubCode")

    }
}