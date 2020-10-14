package com.example.foodlist.utils

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlist.adapter.FoodAdapter
import com.example.foodlist.model.Food

object FoodActions {
    /**
     * @author beatriz.castro
     * Method to insert food into list and notify recyclewview
     * */
    private fun insertFood(foodList: ArrayList<Food>, foodAdapter: FoodAdapter, name: String, price: Double) {
        foodList.add(Food(name, price))
        foodAdapter.notifyItemInserted(foodList.lastIndex)
    }

    /**
     * @author beatriz.castro
     * Method to update food and notify recyclewview about change
     * */
    private fun editFood(foodList: ArrayList<Food>, foodAdapter: FoodAdapter, name: String, price: Double, position: Int) {
        foodList.forEachIndexed { index, _ ->
            if(index == position){
                foodList[index].name = name
                foodList[index].price = price
            }
        }
        foodAdapter.notifyItemChanged(position)
    }

    /**
     * @author beatriz.castro
     * Method to remove food on swipe
     * */
    fun removeFood(viewHolder: RecyclerView.ViewHolder, foodList: ArrayList<Food>, foodAdapter: FoodAdapter){
        foodList.removeAt(viewHolder.adapterPosition)
        foodAdapter.notifyDataSetChanged()
    }

    /**
     * @author beatriz.castro
     * Method to choose action between insert and edit, at onActivityResult
     * */
    fun chooseResultAction(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        foodList: ArrayList<Food>,
        foodAdapter: FoodAdapter
    ) {

        when(requestCode) {

            Constants.INTENT_FOOD_ID -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    data.getStringExtra(Constants.FOOD_NAME)?.let {
                        insertFood(foodList, foodAdapter, it, data.getDoubleExtra(Constants.FOOD_PRICE,0.0)) }
                }
            }

            Constants.INTENT_FOOD_ID_EDIT -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    data.getStringExtra(Constants.FOOD_NAME)?.let {
                        editFood(foodList, foodAdapter, it, data.getDoubleExtra(Constants.FOOD_PRICE, 0.0),
                            data.getIntExtra(Constants.FOOD_INDEX,0))
                    }
                }
            }
        }
    }
}