package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.minorproject.jiitstudysync.databinding.ActivitySignupPageBinding

class SignupPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding : ActivitySignupPageBinding by lazy {
        ActivitySignupPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        // on click event for back arrow button
        binding.signupPageBackBtn.setOnClickListener{
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }

        // Authenticating user Signup btn click event
        auth = FirebaseAuth.getInstance()

        binding.signupBtn.setOnClickListener{
            val email = binding.signupEmail.text.toString()
            val username = binding.signupName.text.toString()
            val enroll = binding.signupEnroll
            val sem = binding.signupSem.text.toString()
            val batch = binding.signupBatch.text.toString()
            val password = binding.signupPassword.text.toString()

            // validating user Inputs
            if(email.isEmpty() || username.isEmpty() ||
                enroll.length() == 0 || sem.isEmpty() ||
                batch.isEmpty() || password.isEmpty()
                ){
                    Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show()
            }else if(batch.length > 3 && batch[0].isLetter()){
                Toast.makeText(this, "Enter a Valid Batch", Toast.LENGTH_SHORT).show()
            }
            else if(sem.toInt() > 10){
                Toast.makeText(this, "Enter a valid Semester", Toast.LENGTH_SHORT).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,
                                "Registered Successfully!!",
                                Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, LoginPage::class.java))
                            finish()
                        }
                        else{
                            Toast.makeText(this,
                                "Registration Failed : ${task.exception?.message}",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        // on click event for Login text in footer
        binding.signupBtnLoginFooter.setOnClickListener{
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}