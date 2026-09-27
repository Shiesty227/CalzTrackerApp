package com.example.calztrackerapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.calztrackerapp.R
import com.example.calztrackerapp.data.model.db.Meal




class MealAdapter(private val onClick: (Meal) -> Unit) :
    ListAdapter<Meal, MealAdapter.MealViewHolder>(DIFF) {

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<Meal>() {
            override fun areItemsTheSame(oldItem: Meal, newItem: Meal) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Meal, newItem: Meal) = oldItem == newItem
        }
    }

    inner class MealViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.mealName)
        val details: TextView = view.findViewById(R.id.mealDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = getItem(position)
        holder.name.text = meal.name
        holder.details.text = "Calories: ${meal.calories} | P: ${meal.protein}g | C: ${meal.carbs}g | F: ${meal.fats}g"
        holder.itemView.setOnClickListener { onClick(meal) }
    }
}
