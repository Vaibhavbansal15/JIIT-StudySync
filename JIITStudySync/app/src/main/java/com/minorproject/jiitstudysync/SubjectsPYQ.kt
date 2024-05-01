package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.minorproject.jiitstudysync.databinding.ActivitySubjectsPyqBinding

class SubjectsPYQ : AppCompatActivity() {

    private val binding : ActivitySubjectsPyqBinding by lazy{
        ActivitySubjectsPyqBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sName : TextView = binding.pyqSubName
        val sCode : TextView = binding.pyqSubCode

        val bundle : Bundle? = intent.extras
        sName.text = bundle!!.getString("pyqSubName")
        sCode.text = bundle.getString("pyqSubCode")
    }
}