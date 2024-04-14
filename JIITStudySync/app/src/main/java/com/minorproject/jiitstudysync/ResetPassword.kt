package com.minorproject.jiitstudysync

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.minorproject.jiitstudysync.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    private val binding : ActivityResetPasswordBinding by lazy {
        ActivityResetPasswordBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.resetPasswordBtn.setOnClickListener{
            val email = binding.resetPasswordEmail.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Enter the email", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Check your mail!!", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(
                                this,
                                "Failed : ${task.exception?.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}