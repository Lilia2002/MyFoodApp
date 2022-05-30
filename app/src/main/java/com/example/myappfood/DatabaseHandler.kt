package com.example.myappfood

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

const val DATABASE_NAME = "MenuDB" //Offline Database

const val COL_ID = "id"

const val OFFLINE_FOOD_MENU_TABLE_NAME = "offline_food_menu"
const val COL_ITEM_NAME = "item_name"
const val COL_ITEM_PRICE = "item_price"
const val COL_ITEM_STAR = "item_star"
const val COL_ITEM_CATEGORY = "item_category"
const val COL_ITEM_IMAGE_URL = "item_image_url"

const val CART_TABLE_NAME = "current_cart"
const val CART_ITEM_ID = "item_id"
const val CART_IMAGE_URL = "image_url"
const val CART_ITEM_NAME = "item_name"
const val CART_ITEM_PRICE = "item_price"
const val CART_ITEM_SHORT_DESC = "item_short_desc"
const val CART_ITEM_STARS = "item_stars"
const val CART_ITEM_QTY = "item_qty"



class DatabaseHandler(val context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {

        val createOfflineMenuTable = "CREATE TABLE $OFFLINE_FOOD_MENU_TABLE_NAME (" +
                "$COL_ITEM_NAME VARCHAR(256), " +
                "$COL_ITEM_PRICE REAL," +
                "$COL_ITEM_CATEGORY VARCHAR(256)," +
                "$COL_ITEM_STAR REAL," +
                "$COL_ITEM_IMAGE_URL VARCHAR(256)" +
                ");"



        db?.execSQL(createOfflineMenuTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }


    fun insertOfflineMenuData(item: ModelMenu) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, item.itemName)
        cv.put(COL_ITEM_PRICE, item.itemPrice)
        cv.put(COL_ITEM_IMAGE_URL, item.imageUrl)
        cv.put(COL_ITEM_STAR, item.itemStars)
        val result = db.insert(OFFLINE_FOOD_MENU_TABLE_NAME, null, cv)
        if (result == (-1).toLong()) {
            Toast.makeText(context, "Failed to Insert Data", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("Range")
    fun readOfflineMenuData(): MutableList<ModelMenu> {
        val list: MutableList<ModelMenu> = ArrayList()

        val db = this.readableDatabase
        val query = "SELECT * from $OFFLINE_FOOD_MENU_TABLE_NAME"
        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val item = ModelMenu()
                item.imageUrl =
                    result.getString(result.getColumnIndex(COL_ITEM_IMAGE_URL)).toString()
                item.itemName = result.getString(result.getColumnIndex(COL_ITEM_NAME)).toString()
                item.itemPrice = result.getFloat(result.getColumnIndex(COL_ITEM_PRICE))
                item.itemStars = result.getFloat(result.getColumnIndex(COL_ITEM_STAR))

                list.add(item)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }

    fun clearTheOfflineMenuTable() {
        try {
            val db = this.writableDatabase
            db.delete(OFFLINE_FOOD_MENU_TABLE_NAME, null, null)
        } catch (e: Exception) {
            Toast.makeText(context, "Unable to delete the records", Toast.LENGTH_SHORT).show()
        }
    }


}
