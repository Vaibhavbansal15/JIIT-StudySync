package com.minorproject.jiitstudysync

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.minorproject.jiitstudysync.databinding.ActivitySignupPageBinding

class SignupPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

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
        database = FirebaseDatabase.getInstance().reference

        binding.signupBtn.setOnClickListener{
            val email = binding.signupEmail.text.toString()
            val username = binding.signupName.text.toString()
            val enroll = binding.signupEnroll.text.toString()
            val sem = binding.signupSem.text.toString()
            val branch = binding.signupBranch.text.toString()
            val password = binding.signupPassword.text.toString()

            // validating user Inputs
            if(email.isEmpty() || username.isEmpty() ||
                enroll.isEmpty() || sem.isEmpty() ||
                branch.isEmpty() || password.isEmpty()
                ){
                    Toast.makeText(this, "Please Fill all the details", Toast.LENGTH_SHORT).show()
            }else if(branch.length > 3){
                Toast.makeText(this, "Enter a Valid Branch", Toast.LENGTH_SHORT).show()
            }
            else if(sem.toInt() > 10){
                Toast.makeText(this, "Enter a valid Semester", Toast.LENGTH_SHORT).show()
            }
            else{

                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("")
                progressDialog.setCancelable(false)
                progressDialog.show()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {task ->
                        if(task.isSuccessful){

                            // verifying the user with email authentication
                            auth.currentUser?.sendEmailVerification()
                                ?.addOnSuccessListener {
                                    Toast.makeText(this,
                                        "Please verify your Email!!",
                                        Toast.LENGTH_SHORT).show()

                                    // saving user data on firebase
                                    val user = UserDetails(email, username, enroll, sem, branch, password)
                                    val userID = auth.currentUser!!.uid
                                    database.child("Users").child(userID).setValue(user)

                                    // updating the UI
                                    progressDialog.dismiss()
                                    startActivity(Intent(this, LoginPage::class.java))
                                    finish()
                                }
                                ?.addOnFailureListener{
                                    progressDialog.dismiss()
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                            }
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