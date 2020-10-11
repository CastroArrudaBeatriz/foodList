package com.example.foodlist

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data_food.*

class ActivityDataFood : AppCompatActivity() {

    companion object {
        const val FOOD_NAME = "name"
        const val FOOD_PRICE = "price"
        const val FOOD_INDEX = "index"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_food)
    }

    override fun onStart() {
        super.onStart()

        checkEditValues()

        add_food_item_button.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(FOOD_NAME, if(!edit_food_name.text.toString().isNullOrEmpty()) edit_food_name.text.toString() else "")
            resultIntent.putExtra(FOOD_PRICE, if(!edit_food_price.text.toString().isNullOrEmpty()) edit_food_price.text.toString().toDouble() else 0.0)
            resultIntent.putExtra(FOOD_INDEX, intent.getIntExtra(FOOD_INDEX , 0 ))
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    private fun checkEditValues(){
        val intent = intent
        edit_food_name.setText(intent.getStringExtra(FOOD_NAME))
        edit_food_price.setText(intent.getDoubleExtra(FOOD_PRICE, 0.0).toString())
    }
}