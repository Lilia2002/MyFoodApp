package com.example.myappfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterActivity: AppCompatActivity() {

    lateinit var btn_register: Button
    lateinit var et_email_register: EditText
    lateinit var et_password_register: EditText
    lateinit var et_name_register: EditText
    lateinit var et_confirm_password_register: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_register = findViewById(R.id.btn_register)
        et_email_register = findViewById(R.id.et_email_register)
        et_password_register = findViewById(R.id.et_password_register)
        et_name_register = findViewById(R.id.et_name_register)
        et_confirm_password_register = findViewById(R.id.et_confirm_password_register)

        btn_register.setOnClickListener {
            val email = et_email_register.text.toString().trim()
            val password = et_password_register.text.toString().trim()
            val name = et_name_register.text.toString().trim()
            val confirm_password = et_confirm_password_register.text.toString().trim()

            if(name.isEmpty()){
                et_name_register.error = "Please enter name"
                et_name_register.requestFocus()
                return@setOnClickListener
            }

            if(email.isEmpty()){
                et_email_register.error = "Please enter email"
                et_email_register.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                et_email_register.error = "Valid Email Required"
                et_email_register.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length<6){
                et_password_register.error = "Please enter minimum 6 character password"
                et_password_register.requestFocus()
                return@setOnClickListener
            }

            if(password != confirm_password){
                et_confirm_password_register.error = "Passwords must match"
                et_confirm_password_register.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)

        }


    }

    private fun registerUser(email: String, password: String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java).apply{
                        val flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }else{
                    Toast.makeText(this@RegisterActivity, "Some Error ", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
