package com.example.myappfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myappfood.databinding.FragmentLogInBinding
import com.google.firebase.auth.FirebaseAuth


class LogInFragment : Fragment() {
    private lateinit var binding: FragmentLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(layoutInflater)

            firebaseAuth = FirebaseAuth.getInstance()
            binding.textView.setOnClickListener {
                findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
            }

            binding.btnLogin.setOnClickListener {
                val email = binding.emailEt.text.toString()
                val pass = binding.passET.text.toString()

                if (email.isNotEmpty() && pass.isNotEmpty()) {

                    firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            findNavController().navigate(R.id.action_logInFragment_to_accountFragment)
                        } else {
                            Toast.makeText(context,
                                it.exception.toString(),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                } else {
                    Toast.makeText(context, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT
                    ).show()

                }

            }
        return binding.root
        }

    override fun onStart() {
        super.onStart()

        if(firebaseAuth.currentUser != null){
            findNavController().navigate(R.id.action_logInFragment_to_accountFragment)
        }

    }


}


