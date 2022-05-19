package com.example.myappfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        lateinit var tv_userid: TextView
        lateinit var tv_emailid: TextView
        lateinit var btnLogout: Button


        tv_userid = findViewById(R.id.tv_user_id)
        tv_emailid = findViewById(R.id.tv_email_id)
        btnLogout = findViewById(R.id.btn_logout)

        tv_userid.text = "User ID :: $userId"
        tv_emailid.text = "Email ID ::$emailId"
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this@AccountActivity, LoginActivity::class.java))
            finish()
        }

    }
    }
