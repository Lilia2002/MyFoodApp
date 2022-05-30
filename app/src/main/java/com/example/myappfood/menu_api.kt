package com.example.myappfood

import android.view.MenuItem

enum class RequestType {
    READ, OFFLINE_UPDATE
}

interface MenuApi {
    fun onFetchSuccessListener(list: ArrayList<ModelMenu>, requestType: RequestType)
}