package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.minorproject.jiitstudysync.databinding.ActivityUploadPyqBinding

class UploadPYQ : AppCompatActivity() {

    private val binding : ActivityUploadPyqBinding by lazy {
        ActivityUploadPyqBinding.inflate(layoutInflater)
    }
    private val contract = registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.pyqDetails.setImageURI(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.selectPyqBtn.setOnClickListener{
            contract.launch("image/*")
        }
        binding.uploadPyqBtn.setOnClickListener{
            Toast.makeText(this, "PYQ uploaded", Toast.LENGTH_SHORT).show()
        }
    }
}