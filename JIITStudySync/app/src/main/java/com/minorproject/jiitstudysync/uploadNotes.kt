package com.minorproject.jiitstudysync

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import com.minorproject.jiitstudysync.databinding.ActivityUploadNotesBinding

class UploadNotes : AppCompatActivity() {


    private lateinit var storgaeReference: StorageReference

    private val binding : ActivityUploadNotesBinding by lazy {
        ActivityUploadNotesBinding.inflate(layoutInflater)
    }
    private var pdfUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        storgaeReference = FirebaseStorage.getInstance().getReference("Uploaded_Notes")

        binding.selectNotesBtn.setOnClickListener{
            launcher.launch("application/pdf")
        }

        binding.uploadNotesBtn.setOnClickListener{
            val subName = binding.notesSubName.text.toString()
            val subCode = binding.notesSubCode.text.toString()
            val desc = binding.notesDescription.text.toString()

            if(subName.isEmpty() || subCode.isEmpty() || desc.isEmpty()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }
            else if(pdfUri == null){
                Toast.makeText(this, "Please select a file first", Toast.LENGTH_SHORT).show()
            }
            else {
                uploadNotes()
            }
        }

        binding.uploadNotesBackBtn.setOnClickListener {
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }
    }

    private fun uploadNotes() {

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val code = binding.notesSubCode.text.toString()
        val fileName = binding.notesSubCode.text.toString()+"_"+binding.notesDescription.text.toString()
        val mStorageRef = storgaeReference.child("$code/$fileName")
        pdfUri?.let {uri ->
            mStorageRef.putFile(uri)
                .addOnSuccessListener {

                    binding.notesSubCode.text.clear()
                    binding.notesSubName.text.clear()
                    binding.notesDescription.text.clear()
                    binding.pdfFileName.setText("Choose File")

                    progressDialog.dismiss()
                    Toast.makeText(this, "File Uploaded Successfully....", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed to Upload!!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()){uri ->
        pdfUri = uri
        val fileName = uri?.let { DocumentFile.fromSingleUri(this, it)?.name }
        binding.pdfFileName.text = fileName.toString()
    }

}