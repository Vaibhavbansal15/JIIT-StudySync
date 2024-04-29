package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.minorproject.jiitstudysync.databinding.ActivityUserDashboardBinding

class UserDashboard : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private val binding : ActivityUserDashboardBinding by lazy {
        ActivityUserDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        binding.logoutBtn.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged Out Successfully!!!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }

        binding.addIcon.setOnClickListener{
            showPopupMenu(it)
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

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.upload_menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.uploadPYQs -> {
                    startActivity(Intent(this@UserDashboard, UploadPYQ::class.java))
//                    Toast.makeText(this, "Upload PYQs", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.uploadNotes -> {
                    startActivity(Intent(this@UserDashboard, UploadNotes::class.java))
//                    Toast.makeText(this, "Upload Notes", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}