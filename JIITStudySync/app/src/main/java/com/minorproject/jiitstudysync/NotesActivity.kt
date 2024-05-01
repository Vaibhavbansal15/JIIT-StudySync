package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.minorproject.jiitstudysync.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var subjectRecyclerView : RecyclerView
    private lateinit var subjectArrayList : ArrayList<SubjectDetails>

    private val binding : ActivityNotesBinding by lazy {
        ActivityNotesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        subjectRecyclerView = binding.subjectList
        subjectRecyclerView.layoutManager = LinearLayoutManager(this)
        subjectRecyclerView.setHasFixedSize(true)

        subjectArrayList = arrayListOf<SubjectDetails>()
        getSubjectData()

        binding.backBtn.setOnClickListener{
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.pyqSection -> {
                    startActivity(Intent(this, PYQActivity::class.java))
                    finish()
                    true
                }
                R.id.notesSection -> {
                    startActivity(Intent(this, NotesActivity::class.java))
                    finish()
                    true
                }
                R.id.forumSection -> {
                    Toast.makeText(this, "Forum Section ", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun getSubjectData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Subjects")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(subSnapshot in snapshot.children){
                        val subject = subSnapshot.getValue(SubjectDetails::class.java)
                        subjectArrayList.add(subject!!)
                    }
                }

                val adapter = SubjectsAdapter(subjectArrayList)
                subjectRecyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : SubjectsAdapter.onItemClickListener{
                    override fun onItemClick(position: Int) {
//                        val intent = Intent(this@NotesActivity, SubjectsPYQ::class.java)
//                        startActivity(intent)
                    }

                })

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@NotesActivity, "Failed to fetch Subjects", Toast.LENGTH_SHORT).show()
            }
        })
    }
}