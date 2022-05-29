package com.matsuyami.recipy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.RecipeInfoAdapter
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.utils.Resource
import com.matsuyami.recipy.viewmodels.RecipeSearchVM

class RecipeSearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var rvRecipeInfo : RecyclerView
    private lateinit var viewModel: RecipeSearchVM
    private lateinit var recipeInfoAdapter : RecipeInfoAdapter

    val TAG = "RecipeSearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipesearch)

        val recipeRepo = RecipeSearchRepo()
        val viewModelProviderFactory = RecipeSearchProvider(recipeRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipeSearchVM::class.java]

        viewModel.recipes.observe(this, Observer{response ->
            when(response){
                is Resource.Success -> {
                   hideProgressBar()
                    response.data?.let{ recipeResp ->
                        recipeInfoAdapter.differ.submitList(recipeResp.results.filter{
                            it.description != null &&
                            it.description.isNotEmpty() &&
                            it.totalTimeTier != null
                        })
                        Log.d(TAG, recipeResp.results.toString())
                    }
                }
                is Resource.Error ->{
                    response.message?.let { msg ->
                        Log.e(TAG, "An error occurred: $msg")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
        setupRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        setupSearchView(menuItem)

        return super.onCreateOptionsMenu(menu)
    }
    private fun setupRecyclerView(){
        recipeInfoAdapter = RecipeInfoAdapter()
        rvRecipeInfo = findViewById(R.id.rvRecipeInfo)
        rvRecipeInfo.apply {
            adapter = recipeInfoAdapter
            layoutManager = FlexboxLayoutManager(this@RecipeSearchActivity).apply{
            justifyContent = JustifyContent.SPACE_EVENLY
            alignItems= AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
            }
        }
    }

    private fun hideProgressBar(){
    }

    private fun showProgressBar(){
    }

    private fun setupSearchView(menuItem : MenuItem?){
        searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search an ingredient..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let{ viewModel.getRecipes(it) }
                return false
            }
        })
    }
}