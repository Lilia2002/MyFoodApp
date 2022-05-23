package com.example.myappfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2


class IntroTwoFragment : Fragment() {
    private lateinit var next2: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro_two, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        next2 = view.findViewById(R.id.next2)
        next2.setOnClickListener {
            findNavController().navigate(R.id.action_introTwoFragment2_to_introThree1Fragment)
        }
        return view
    }


    }