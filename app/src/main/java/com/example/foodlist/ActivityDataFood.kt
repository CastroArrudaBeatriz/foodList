package com.example.foodlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.foodlist.utils.Constants
import kotlinx.android.synthetic.main.activity_data_food.*

class ActivityDataFood : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_food)
    }

    override fun onStart() {
        super.onStart()
        checkEditValues()
        saveButtonListener()
    }

    /**
     * @author beatriz.castro
     * method to check put parameters and set on fields
     * */
    private fun checkEditValues(){
        val intent = intent
        edit_food_name.setText(intent.getStringExtra(Constants.FOOD_NAME))
        edit_food_price.setText(intent.getDoubleExtra(Constants.FOOD_PRICE, 0.0).toString())
    }

    /**
     * @author beatriz.castro
     * save button listener
     * make a intent and put parameters to return and insert/edit item at food list
     * */
    private fun saveButtonListener(){
        add_food_item_button.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(Constants.FOOD_NAME, if(isValidString(edit_food_name.text.toString())) edit_food_name.text.toString() else "")
            resultIntent.putExtra(Constants.FOOD_PRICE, if(isValidString(edit_food_price.text.toString())) edit_food_price.text.toString().toDouble() else 0.0)
            resultIntent.putExtra(Constants.FOOD_INDEX, intent.getIntExtra(Constants.FOOD_INDEX , 0 ))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun isValidString(value: String) = !value.isNullOrEmpty()
}