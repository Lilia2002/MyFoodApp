package com.example.myappfood


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappfood.databinding.FragmentMenuBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MenuFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private val db = DatabaseHandler(context)
    private lateinit var foodItemAdapter: FoodItemAdapter
    private var allItems = ArrayList<ModelMenu>()
    private lateinit var itemRecyclerView: RecyclerView
    private lateinit var searchBox: SearchView
    lateinit var binding: FragmentMenuBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        itemRecyclerView=view?.findViewById(R.id.items_recycler_view)!!
        binding = FragmentMenuBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = mutableListOf<ModelMenu>(
            ModelMenu("1", "1", 1.0F, 1.0F, 1),
            ModelMenu("2", "1", 1.0F, 1.0F, 1),
            ModelMenu("3", "1", 1.0F, 1.0F, 1),
            ModelMenu("4", "1", 1.0F, 1.0F, 1),
        )
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(this.context)
        foodItemAdapter = FoodItemAdapter(list)
        binding.itemsRecyclerView.adapter = foodItemAdapter
    }
    private fun loadMenu() {
            val db = Firebase.firestore
        Firebase.auth.currentUser?.let {
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    //  CoroutineScope(Dispatchers.IO).launch {
                    allItems  = arrayListOf()
                    for (document in result) {
                        allItems += (
                                ModelMenu(
                                    document.get("item_name").toString(),
                                    document.get("picture").toString(),
                                    document.get("Stars").toString().toFloat(),
                                    document.get("price").toString().toFloat(),
                                )
                                )
                    }

                }
        }
            ?.addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
            }
    private fun startAdapter(){
        //adapter=
    }

        }

