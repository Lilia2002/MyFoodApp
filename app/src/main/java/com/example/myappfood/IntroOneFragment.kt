package com.example.myappfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2


class IntroOneFragment : Fragment() {
    private lateinit var next: TextView
    private lateinit var skip:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro_one, container, false)
        skip=view.findViewById(R.id.skip)
        next = view.findViewById(R.id.next)
        next.setOnClickListener {
            findNavController().navigate(R.id.action_introOneFragment2_to_introTwoFragment)
        }
        skip.setOnClickListener{
            findNavController().navigate(R.id.action_introOneFragment2_to_signUpFragment2)
        }

        return view
    }
}