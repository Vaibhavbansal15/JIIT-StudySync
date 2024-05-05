package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.GridLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.minorproject.jiitstudysync.databinding.ActivitySubjectNotesBinding

class SubjectNotes : AppCompatActivity() {

    private val binding : ActivitySubjectNotesBinding by lazy{
        ActivitySubjectNotesBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    private lateinit var notesAdapter : SubjectNotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sName : TextView = binding.notesSubName
        val sCode : TextView = binding.notesSubCode

        val bundle : Bundle? = intent.extras
        sName.text = bundle!!.getString("notesSubName")
        sCode.text = bundle.getString("notesSubCode")

        databaseReference = FirebaseDatabase.getInstance().reference.child("Subjects").child("${sCode.text}").child("notes")

        binding.notesRecyclerView.setHasFixedSize(true)
        binding.notesRecyclerView.layoutManager = GridLayoutManager(this, 1)
        notesAdapter = SubjectNotesAdapter()
        binding.notesRecyclerView.adapter = notesAdapter
        getAllNotes()

    }

    private fun getAllNotes() {
        databaseReference.addValueEventListener(object : ValueEventListener{

            val tempList = mutableListOf<NotesFile>()
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val file = it.getValue(NotesFile::class.java)
                    if(file != null) {
                        tempList.add(file)
                    }
                }
                if(tempList.isEmpty()){
                    Toast.makeText(this@SubjectNotes, "No Data Found", Toast.LENGTH_SHORT).show()
                }
                notesAdapter.submitList(tempList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SubjectNotes, error.message.toString(), Toast.LENGTH_SHORT).show()
            }

        })
    }
}