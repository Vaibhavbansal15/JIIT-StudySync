package com.minorproject.jiitstudysync

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.minorproject.jiitstudysync.databinding.ActivityUploadNotesBinding

class UploadNotes : AppCompatActivity() {

    private val binding : ActivityUploadNotesBinding by lazy {
        ActivityUploadNotesBinding.inflate(layoutInflater)
    }
    private var pdfUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.selectNotesBtn.setOnClickListener{
            launcher.launch("application/pdf")
        }

        binding.uploadNotesBtn.setOnClickListener{
            val subName = binding.notesSubName.text.toString()
            val subCode = binding.notesSubCode.text.toString()
            val desc = binding.notesDescription.text.toString()

            if(subName.isEmpty() || subCode.isEmpty() || desc.isEmpty()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, "Notes uploaded", Toast.LENGTH_SHORT).show()
            }
        }

        binding.uploadNotesBackBtn.setOnClickListener {
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        pdfUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.pdfFileName.text = fileName.toString()
    }

}