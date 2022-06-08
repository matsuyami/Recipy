package com.matsuyami.recipy.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matsuyami.recipy.R
import com.recipy.models.ComponentX

class IngredientsAdapter(private val components : List<ComponentX>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ingredientText : TextView = itemView.findViewById(R.id.ingredientText)
        val addIcon : ImageView = itemView.findViewById(R.id.ivAddToList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ingredientText.text = components[position].rawText
        Glide.with(holder.itemView)
            .load(R.drawable.add_icon_foreground)
            .into(holder.addIcon)
    }

    override fun getItemCount() = components.size
}