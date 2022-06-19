package com.matsuyami.recipy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.preferences.core.Preferences
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

class FavoritesActivity : AppCompatActivity() {
    private lateinit var rvFavorites: RecyclerView
    private lateinit var recipeAdapter: RecipeInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        setupRecyclerView()

        val infoRepo = RecipeInfoRepo(this@FavoritesActivity)
        val provider = FavoritesProvider(infoRepo)
        val viewModel = ViewModelProvider(this, provider)[RecipeDataVM::class.java]
        viewModel.getAll()

        recipeAdapter.setOnItemClickListener {
            val intent = Intent(this@FavoritesActivity, RecipeInfoActivity::class.java)
            intent.apply{
                putExtra("recipeInfo", it)
            }
            startActivity(intent)
        }

        viewModel.data.observe(this, Observer{
            val recipes = it.asMap().values.map { value ->
                Gson().fromJson(value.toString(), RecipeInfo::class.java)
            }.toList()
            recipeAdapter.differ.submitList(recipes)
//               // TODO: all values are appearing below show on ui later
//               Log.d("fav", Gson().fromJson(value.toString(), RecipeInfo::class.java).toString())
//            }
        })
    }

    private fun setupRecyclerView() {
        rvFavorites = findViewById(R.id.favoriteItems)
        recipeAdapter = RecipeInfoAdapter()
        rvFavorites.apply {
            adapter = recipeAdapter
            layoutManager = FlexboxLayoutManager(this@FavoritesActivity).apply {
                justifyContent = JustifyContent.SPACE_EVENLY
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }
        }
    }
}