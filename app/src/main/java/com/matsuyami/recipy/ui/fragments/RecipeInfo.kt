package com.matsuyami.recipy.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.IngredientsAdapter
import com.matsuyami.recipy.adapters.InstructionsAdapter
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.matsuyami.recipy.viewmodels.RecipeDataVM
import com.recipy.models.RecipeInfo

class RecipeInfo : Fragment(R.layout.fragment_recipe_info) {
    private lateinit var rvInstructions: RecyclerView
    private lateinit var rvIngredients: RecyclerView
    private lateinit var btnFavorites : Button
    private lateinit var ivRecipeInfo : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivRecipeInfo = view.findViewById(R.id.ivRecipeInfo)
        rvInstructions = view.findViewById(R.id.rvInstructions)
        btnFavorites = view.findViewById(R.id.btnFavorites)
        rvIngredients = view.findViewById(R.id.rvIngredients)

        val recipeInfo = arguments?.getSerializable("recipeInfo") as RecipeInfo
        val dataVM = RecipeDataVM(RecipeInfoRepo(requireContext()))
        setupFoodIV(recipeInfo)
        setupInstructionsRV(recipeInfo)
        setupIngredientsRV(recipeInfo)
        setupBtn(dataVM, recipeInfo)
        Log.d("RecipeInfoFragment", "onViewCreated")
    }

    private fun setupFoodIV(recipeInfo : RecipeInfo){
        Glide.with(this)
            .load(recipeInfo.thumbnailUrl)
            .into(ivRecipeInfo)
    }

    private fun setupInstructionsRV(recipeInfo: RecipeInfo) {
        val instructionsAdapter = InstructionsAdapter(recipeInfo.instructions)
        rvInstructions.apply {
            adapter = instructionsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupIngredientsRV(recipeInfo: RecipeInfo){
        val ingredientsAdapter = IngredientsAdapter(recipeInfo.sections.first().components)
        rvIngredients.apply {
            adapter = ingredientsAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply{

            }
        }
    }


    private fun setupBtn(dataVM : RecipeDataVM, recipeInfo : RecipeInfo){
        btnFavorites.setOnClickListener {
            dataVM.saveRecipeInfo(recipeInfo)
            Toast.makeText(requireContext(),
                "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
}