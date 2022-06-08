package com.matsuyami.recipy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.IngredientsAdapter
import com.matsuyami.recipy.adapters.InstructionsAdapter
import com.recipy.models.RecipeInfo

class RecipeInfoActivity : AppCompatActivity() {
    private lateinit var rvInstructions: RecyclerView
    private lateinit var rvIngredients: RecyclerView
    private lateinit var ivFood: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_info)
        val recipeInfo = intent.getSerializableExtra("recipeInfo") as RecipeInfo

        setupFoodIV(recipeInfo)
        setupInstructionsRV(recipeInfo)
        setupIngredientsRV(recipeInfo)
    }

    private fun setupFoodIV(recipeInfo : RecipeInfo){
        ivFood = findViewById(R.id.ivRecipeInfo)
        Glide.with(this@RecipeInfoActivity)
            .load(recipeInfo.thumbnailUrl)
            .into(ivFood)
    }

    private fun setupInstructionsRV(recipeInfo: RecipeInfo) {
        val instructionsAdapter = InstructionsAdapter(recipeInfo.instructions)
        rvInstructions = findViewById(R.id.rvInstructions)
        rvInstructions.apply {
            adapter = instructionsAdapter
            layoutManager = LinearLayoutManager(this@RecipeInfoActivity)
        }
    }

    private fun setupIngredientsRV(recipeInfo: RecipeInfo){
        val ingredientsAdapter = IngredientsAdapter(recipeInfo.sections.first().components)
        rvIngredients = findViewById(R.id.rvIngredients)
        rvIngredients.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(this@RecipeInfoActivity)
        }
    }
}