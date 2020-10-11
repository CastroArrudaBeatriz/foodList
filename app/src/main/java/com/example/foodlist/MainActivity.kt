package com.example.foodlist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodlist.adapter.FoodAdapter
import com.example.foodlist.model.Food
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    /**
     * final values
     * */
    companion object {
        const val INTENT_FOOD_ID = 1
        const val INTENT_FOOD_ID_EDIT = 2
        const val FOOD_NAME = "name"
        const val FOOD_PRICE = "price"
        const val FOOD_INDEX = "index"
        const val FOOD_LIST_KEY = "food_list_key"
    }

    /**
     * initial food list
     * */
    private val foodList = arrayListOf<Food>(
        Food("Banana", 2.50),
        Food("Maracujá", 4.00),
        Food("Couve", 2.00)
    )

    /**
     * inicializar adapter, passando o contexto, lista e metodo de callback
     * */
    private val foodAdapter = FoodAdapter(this, foodList, this::onFoodClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inciar o recycleview, setando quem será o adapter e o gerenciador de layout
        setupFoodList()

        // listener botao adicionar
        add_food_button.setOnClickListener {
            val intent = Intent(this,  ActivityDataFood::class.java)
            startActivityForResult(intent, INTENT_FOOD_ID)
        }
    }

    /**
     * metodo de callback que será chamado pelo adapter quando item for clicado
     * */
    private fun onFoodClickListener(food: Food, position: Int) {
        val intent = Intent(this,  ActivityDataFood::class.java)
        intent.putExtra(FOOD_NAME , food.name)
        intent.putExtra(FOOD_PRICE, food.price)
        intent.putExtra(FOOD_INDEX, position)
        startActivityForResult(intent, INTENT_FOOD_ID_EDIT)
    }

    /**
     * metodo para fazer o bind do adapter e informar o layout
     * */
    private fun setupFoodList(){
        list_food.adapter = foodAdapter;
        val layoutManager = GridLayoutManager(this, 1)
        list_food.layoutManager = layoutManager
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == INTENT_FOOD_ID && resultCode == Activity.RESULT_OK){
            if(data != null){
                data.getStringExtra(FOOD_NAME)?.let { insertFood(it, data.getDoubleExtra(FOOD_PRICE,0.0)) }
            }
        }else if(requestCode == INTENT_FOOD_ID_EDIT && resultCode == Activity.RESULT_OK){
            if(data != null){
                data.getStringExtra(FOOD_NAME)?.let { editFood(it,
                    data.getDoubleExtra(FOOD_PRICE,0.0),
                    data.getIntExtra(FOOD_INDEX, 0)) }
            }
        }
    }

    private fun insertFood(name: String, price: Double) {
        foodList.add(Food(name, price))
        foodAdapter.notifyItemInserted(foodList.lastIndex)
    }

    private fun editFood(name: String, price: Double, position: Int) {
        foodList.forEachIndexed { index, _ ->
            if(index == position){
                foodList[index].name = name
                foodList[index].price = price
            }
        }
        foodAdapter.notifyItemChanged(position)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(FOOD_LIST_KEY, foodList)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        foodList.clear()
        savedInstanceState.getParcelableArrayList<Food>(FOOD_LIST_KEY)?.let { foodList.addAll(it) }
    }

}