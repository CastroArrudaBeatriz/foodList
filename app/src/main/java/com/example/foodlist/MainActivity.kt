package com.example.foodlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodlist.adapter.FoodAdapter
import com.example.foodlist.model.Food
import com.example.foodlist.utils.Constants
import com.example.foodlist.utils.FoodActions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val foodList = arrayListOf<Food>(
        Food("Banana", 2.50),
        Food("Maracujá", 4.00),
        Food("Couve", 2.00)
    )

    private val foodAdapter = FoodAdapter(this, foodList, this::onFoodClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupFoodList()
        addButtonListener()
    }

    /**
     * @author beatriz.castro
     * Method to bind adapter e layout manager of recyclerview
     * */
    private fun setupFoodList(){
        list_food.adapter = foodAdapter;
        val layoutManager = GridLayoutManager(this, 1)
        list_food.layoutManager = layoutManager
    }

    /**
     * @author beatriz.castro
     * Method to open second activity at add button
     * */
    private fun addButtonListener(){
        add_food_button.setOnClickListener {
            val intent = Intent(this,  ActivityDataFood::class.java)
            startActivityForResult(intent, Constants.INTENT_FOOD_ID)
        }
    }

    /**
     * @author beatriz.castro
     * Method to open second activity at click item, put extra parameters for second activity set at fields
     * */
    private fun onFoodClickListener(food: Food, position: Int) {
        val intent = Intent(this,  ActivityDataFood::class.java)
        intent.putExtra(Constants.FOOD_NAME , food.name)
        intent.putExtra(Constants.FOOD_PRICE, food.price)
        intent.putExtra(Constants.FOOD_INDEX, position)
        startActivityForResult(intent, Constants.INTENT_FOOD_ID_EDIT)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        FoodActions.chooseResultAction(requestCode, resultCode, data, foodList, foodAdapter)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(Constants.FOOD_LIST_KEY, foodList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        foodList.clear()
        savedInstanceState.getParcelableArrayList<Food>(Constants.FOOD_LIST_KEY)?.let { foodList.addAll(it) }
    }

}