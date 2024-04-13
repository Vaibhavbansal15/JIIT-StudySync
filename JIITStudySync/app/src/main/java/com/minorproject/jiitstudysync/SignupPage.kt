package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class SignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup_page)

        val signupBtn : Button = findViewById(R.id.signupBtn)
        val footerSignupBtn = findViewById<TextView>(R.id.signupBtnLoginFooter)
        val backBtn : ImageButton = findViewById(R.id.signup_page_back_btn)

        footerSignupBtn.setOnClickListener{
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
            finish()
        }
        backBtn.setOnClickListener{
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
}