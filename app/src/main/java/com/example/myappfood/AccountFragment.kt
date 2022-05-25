package com.example.myappfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope


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


        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

        }
        return view
    }
    private fun readDataFirestore() {
        val db = Firebase.firestore
        Firebase.auth.currentUser?.let {
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    var usersInfo: UserInfo
                    for (document in result) {
                        if ((document.get("idUser")
                                .toString()) == (FirebaseAuth.getInstance().uid).toString()
                        ) {


                                document.get("name").toString()
                                document.get("email").toString()
                                document.get("userURLtoImage").toString()



                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(
                        "TAG",
                        "Error getting documents.",
                        exception
                    )
                }
        }
    }
}

