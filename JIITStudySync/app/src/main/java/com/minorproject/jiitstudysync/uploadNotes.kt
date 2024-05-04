package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.minorproject.jiitstudysync.databinding.ActivityUploadNotesBinding

class UploadNotes : AppCompatActivity() {

    private val binding : ActivityUploadNotesBinding by lazy {
        ActivityUploadNotesBinding.inflate(layoutInflater)
    }
//    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
//        binding.imgDetails.setImageURI(it)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding..setOnClickListener{
//            contract.launch("image/*")
//        }

        binding.uploadNotesBtn.setOnClickListener{
            Toast.makeText(this, "Notes uploaded", Toast.LENGTH_SHORT).show()
        }

        binding.uploadNotesBackBtn.setOnClickListener {
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }
    }
}