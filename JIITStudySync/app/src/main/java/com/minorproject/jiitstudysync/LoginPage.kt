package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_page)

        val loginBtn = findViewById<Button>(R.id.loginBtn)
        val footerSignupBtn : TextView = findViewById(R.id.signupBtnLoginFooter)
        val forgotLoginPass : TextView = findViewById(R.id.loginForgotPass)

        footerSignupBtn.setOnClickListener{
            val intent = Intent(this,SignupPage::class.java)
            startActivity(intent)
            finish()
        }

        forgotLoginPass.setOnClickListener{
            startActivity(Intent(this, ResetPassword::class.java))
        }
    }
}