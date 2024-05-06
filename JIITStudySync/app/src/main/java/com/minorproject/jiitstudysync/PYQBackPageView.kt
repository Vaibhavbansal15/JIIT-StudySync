package com.minorproject.jiitstudysync

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.minorproject.jiitstudysync.databinding.ActivityPyqbackPageViewBinding
import java.io.File

class PYQBackPageView : AppCompatActivity() {

    private val binding : ActivityPyqbackPageViewBinding by lazy {
        ActivityPyqbackPageViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sCode = intent.extras?.getString("subCode")
        val imageName = intent.extras?.getString("backPage")
        val storageRef = FirebaseStorage.getInstance().reference.child("Stored_PYQs/$sCode/$imageName.jpg")
        val localFile = File.createTempFile("frontImage", "jpg")
        storageRef.getFile(localFile)
            .addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
                    binding.PyqBackPageView.setImageBitmap(bitmap)
            }.addOnFailureListener {
                Toast.makeText(
                    this@PYQBackPageView,
                    "Failed to Retrieve data",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}