package com.example.foodlist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodlist.R
import com.example.foodlist.model.Food
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.reflect.KFunction2

class FoodAdapter(private val context: Context,
                  private val foods: List<Food>,
                  private val callback: KFunction2<Food, Int, Unit>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    /**
     * @author beatriz.castro
     * make struct to item
     * */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val foodImageView: ImageView = view.imageView
        val foodName: TextView = view.food_name
        val foodPrice: TextView = view.food_price
    }

    /**
     * @author beatriz.castro
     * inflate recyclerview, use viewHolder to item base
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutReference = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        val viewHolder = ViewHolder(layoutReference)

        viewHolder.itemView.setOnClickListener {
            val food = foods[viewHolder.adapterPosition]
            callback(food , viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val(name, price) = foods[position]
        holder.foodImageView.setImageResource(R.drawable.ic_baseline_fastfood_24)
        holder.foodName.text = name
        holder.foodPrice.text = price.toString()
    }
}

