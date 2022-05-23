package com.example.myappfood

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.myappfood.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenFragment : Fragment() {


    private lateinit var binding: FragmentSplashScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000L)
            findNavController().navigate(R.id.action_splashScreenFragment_to_introOneFragment2)
        }
        return view

    }
}

