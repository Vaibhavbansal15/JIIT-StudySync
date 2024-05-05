package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.minorproject.jiitstudysync.databinding.ActivitySubjectsPyqBinding

class SubjectsPYQ : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    private val binding : ActivitySubjectsPyqBinding by lazy{
        ActivitySubjectsPyqBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sName : TextView = binding.pyqSubName
        val sCode : TextView = binding.pyqSubCode

        val bundle : Bundle? = intent.extras
        sName.text = bundle!!.getString("pyqSubName")
        sCode.text = bundle.getString("pyqSubCode")

        databaseReference = FirebaseDatabase.getInstance().reference.child("Subjects").child("${sCode.text}").child("pyqs")
        binding.subjectPyqs.setHasFixedSize(true)
        binding.subjectPyqs.layoutManager = GridLayoutManager(this, 2)

        
    }
}