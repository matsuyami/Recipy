package com.matsuyami.recipy.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matsuyami.recipy.R
import com.recipy.models.RecipeInfo

class RecipeInfoAdapter : RecyclerView.Adapter<RecipeInfoAdapter.RecipeViewHolder>(){
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvRecipeHeader: TextView = itemView.findViewById(R.id.tvHeader)
//        private val tvCookingTime : TextView = itemView.findViewById(R.id.tvCookingTime)
//        private val tvDescription : TextView = itemView.findViewById(R.id.tvDescription)

        private val ivFood : ImageView = itemView.findViewById(R.id.ivFood)
        fun bind(item: RecipeInfo){
            tvRecipeHeader.text = item.name
//            tvDescription.text = item.description
            Glide.with(itemView).load(item.thumbnailUrl).into(ivFood)

            // Could make another screen so that this will never be null
//            tvCookingTime.text = item.totalTimeTier.displayTier
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<RecipeInfo>(){
        override fun areItemsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) : Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeInfo, newItem: RecipeInfo) : Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecipeViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipeInfo = differ.currentList[position]
        holder.bind(recipeInfo)
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", differ.currentList.size.toString())
        return differ.currentList.size
    }

}