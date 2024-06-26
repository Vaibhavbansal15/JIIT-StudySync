package com.minorproject.jiitstudysync

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.minorproject.jiitstudysync.databinding.ActivityLoginPageBinding

class LoginPage : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private val binding : ActivityLoginPageBinding by lazy {
        ActivityLoginPageBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        val currUser : FirebaseUser? = auth.currentUser
        val verified = auth.currentUser?.isEmailVerified
        if(currUser != null && verified == true){
            startActivity(Intent(this, UserDashboard::class.java))
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginBtn.setOnClickListener{
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPass.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Enter all the Details", Toast.LENGTH_SHORT).show()
            }
            else{
                val progressDialog = ProgressDialog(this)
                progressDialog.setMessage("Logging in")
                progressDialog.setCancelable(false)
                progressDialog.show()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val verified = auth.currentUser?.isEmailVerified
                        if(verified == true) {
                            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT)
                                .show()
                            progressDialog.dismiss()
                            startActivity(Intent(this, UserDashboard::class.java))
                            finish()
                        }else{
                            progressDialog.dismiss()
                            Toast.makeText(this, "Please verify your Email", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(
                            this,
                            "Login Failed : ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
        }

        binding.loginForgotPass.setOnClickListener{
            startActivity(Intent(this, ResetPassword::class.java))
        }

        binding.signupBtnLoginFooter.setOnClickListener{
            val intent = Intent(this,SignupPage::class.java)
            startActivity(intent)
            finish()
        }
    }
}