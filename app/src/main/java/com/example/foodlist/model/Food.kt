package com.example.foodlist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Food(var name: String , var price: Double) : Parcelable