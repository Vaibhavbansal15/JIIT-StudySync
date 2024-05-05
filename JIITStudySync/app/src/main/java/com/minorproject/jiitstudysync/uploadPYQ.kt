package com.minorproject.jiitstudysync

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.minorproject.jiitstudysync.databinding.ActivityUploadPyqBinding

@Suppress("DEPRECATION")
class UploadPYQ : AppCompatActivity() {

    private val binding : ActivityUploadPyqBinding by lazy {
        ActivityUploadPyqBinding.inflate(layoutInflater)
    }

    private lateinit var imageUri : Uri
    private var storageReference = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        storageReference = FirebaseStorage.getInstance()

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                binding.pyqImage.setImageURI(it)
                imageUri = it!!
            }
        )

        binding.selectImg.setOnClickListener {
            val fileName : String = binding.pyqSubCode.text.toString()+"_"+binding.pyqExam.text.toString()+"_"+binding.pyqYear.text.toString()
            binding.imgName.setText(fileName)
            galleryImage.launch("image/*")
        }

        binding.uploadPyqBtn.setOnClickListener{

            val subName = binding.pyqSubName.text.toString()
            val subCode = binding.pyqSubCode.text.toString()
            val pyqYear = binding.pyqYear.text.toString()
            val pyqExam = binding.pyqExam.text.toString()

            if(subName.isNotEmpty() || subCode.isNotEmpty() || pyqYear.isNotEmpty() || pyqExam.isNotEmpty()) {
                if (pyqExam == "T1" || pyqExam == "T2" || pyqExam == "T3") {
                    uploadImage()
                } else {
                    Toast.makeText(this, "Enter a valid Examination", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }
        }

        binding.uploadPyqBackBtn.setOnClickListener {
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }
    }

    private fun uploadImage() {
        // showing progress dialog
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val subjectCode = binding.pyqSubCode.text.toString()
        val fileName : String = binding.pyqSubCode.text.toString()+"_"+binding.pyqExam.text.toString()+"_"+binding.pyqYear.text.toString()
        storageReference.getReference("Uploaded_PYQs").child("$subjectCode/$fileName").putFile(imageUri)
            .addOnSuccessListener {
                progressDialog.dismiss()
                binding.pyqSubName.text.clear()
                binding.pyqSubCode.text.clear()
                binding.pyqYear.text.clear()
                binding.pyqExam.text.clear()
                binding.pyqImage.setImageURI(null)
                Toast.makeText(this, "Image Uploaded Successfully....", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                progressDialog.dismiss()
                Toast.makeText(this, "Failed to Upload!!", Toast.LENGTH_SHORT).show()
            }

    }
}