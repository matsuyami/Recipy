package com.matsuyami.recipy.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.google.gson.Gson
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.RecipeInfoAdapter
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.matsuyami.recipy.viewmodels.RecipeDataVM
import com.recipy.models.RecipeInfo

class Favorites : Fragment(R.layout.fragment_favorites) {
    private lateinit var rvFavorites: RecyclerView
    private lateinit var recipeAdapter: RecipeInfoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)

        val infoRepo = RecipeInfoRepo(requireContext())
        val provider = FavoritesProvider(infoRepo)
        val viewModel = ViewModelProvider(this, provider)[RecipeDataVM::class.java]
        viewModel.getAll()

        recipeAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("recipeInfo", it)
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            val recipeInfoFragment = RecipeInfo()
            recipeInfoFragment.arguments = bundle
            transaction?.replace(R.id.fragment_recipe_search, recipeInfoFragment)
            transaction?.addToBackStack(null)
            transaction?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction?.commit()
        }

        viewModel.data.observe(requireActivity(),Observer{
            val recipes = it.asMap().values.map { value ->
                Gson().fromJson(value.toString(), RecipeInfo::class.java)
            }.toList()

            Log.d("FavoriteFragment", "recipes: $recipes")
            recipeAdapter.differ.submitList(recipes)
        })
    }

    private fun setupRecyclerView(view : View) {
        rvFavorites = view.findViewById(R.id.favoriteItems)
        recipeAdapter = RecipeInfoAdapter()
        rvFavorites.apply {
            adapter = recipeAdapter
            layoutManager = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.SPACE_EVENLY
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
        }
    }
}