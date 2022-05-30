package com.example.myappfood

import android.app.ProgressDialog
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.*

class MenuList {
    private var databaseRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    private val foodMenu = "food_menu"
    fun readAllMenu(menuApi: MenuApi, requestType: RequestType) {
//        val menuList = ArrayList<ModelMenu>()

        val menuDbRef = databaseRef.child(foodMenu)
        menuDbRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    val item = ModelMenu(
                        imageUrl = snap.child("item_image_url").value.toString(),
                        itemName = snap.child("item_name").value.toString(),
                        itemPrice = snap.child("item_price").value.toString().toFloat(),
                        itemStars = snap.child("stars").value.toString().toFloat()
                    )
                   DataList.menuList.add(item)
                }

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun insertModelMenu(item: MenuList) {
        val menuRef = databaseRef.child(foodMenu)

        menuRef.setValue(item)
    }

}