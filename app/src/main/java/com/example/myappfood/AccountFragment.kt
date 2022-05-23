package com.example.myappfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


class AccountFragment : Fragment() {
    lateinit var tv_userid: TextView
    lateinit var tv_emailid: TextView
    lateinit var btnLogout: Button

    private var userId: String? = null
    private var emailId: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString("user_id")
            emailId = it.getString("email_id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        tv_userid = view.findViewById(R.id.tv_user_id)
        tv_emailid = view.findViewById(R.id.tv_email_id)
        btnLogout = view.findViewById(R.id.btn_logout)

        tv_userid.text = "User ID :: $userId"
        tv_emailid.text = "Email ID ::$emailId"
        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

        }
        return view
    }
}

