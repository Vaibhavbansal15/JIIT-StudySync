package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.minorproject.jiitstudysync.databinding.ActivityNotesBinding

class NotesActivity : AppCompatActivity() {

    private val binding : ActivityNotesBinding by lazy {
        ActivityNotesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

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
}