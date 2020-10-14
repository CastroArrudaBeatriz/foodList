package com.example.foodlist.utils

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

abstract class SwipeToDelete(context: Context, dragDirection: Int, swipeDirection: Int)
    : ItemTouchHelper.SimpleCallback(dragDirection, swipeDirection){

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //TODO: implement drag function here - beatriz.castro
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

}