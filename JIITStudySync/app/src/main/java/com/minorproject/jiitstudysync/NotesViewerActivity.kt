package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.minorproject.jiitstudysync.databinding.ActivityNotesViewerBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class NotesViewerActivity : AppCompatActivity() {

    private val binding : ActivityNotesViewerBinding by lazy {
        ActivityNotesViewerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fileName = intent.extras?.getString("fileName")
        val downloadUrl = intent.extras?.getString("downloadUrl")

        lifecycleScope.launch(Dispatchers.IO) {
            val inputStream = URL(downloadUrl).openStream()
            withContext(Dispatchers.Main){
                binding.notesPDFView.fromStream(inputStream).load()
            }
        }
    }
}