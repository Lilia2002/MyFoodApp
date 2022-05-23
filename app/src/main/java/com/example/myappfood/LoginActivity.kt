package com.example.myappfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myappfood.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
  private  lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.tvPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.etEmail.error = "Please enter email"
                binding.etEmail.requestFocus()
                    
            }
            if(password.isEmpty() || password.length<6){
                binding.tvPassword.error = "Please enter minimum 6 character password "
                binding.tvPassword.requestFocus()
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmail.error = "Valid Email Required"
                binding.etEmail.requestFocus()
                return@setOnClickListener
            }
            loginUser(email, password)
        }


    }

    private fun loginUser(email: String, password: String){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java).apply{
                        val flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }else{
                    Toast.makeText(this@LoginActivity, "Some error", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onStart() {
        super.onStart()

        FirebaseAuth.getInstance().currentUser?.let {
            val intent = Intent(this@LoginActivity,MainActivity::class.java).apply{
                val flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
        }
    }

}