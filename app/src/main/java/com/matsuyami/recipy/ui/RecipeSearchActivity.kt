package com.matsuyami.recipy.ui

import android.content.Intent
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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.RecipeInfoAdapter
import com.matsuyami.recipy.data.repositories.RecipeInfoRepo
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.utils.Resource
import com.matsuyami.recipy.viewmodels.RecipeVM

class RecipeSearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var rvRecipeInfo : RecyclerView
    private lateinit var viewModel: RecipeVM
    private lateinit var recipeInfoAdapter : RecipeInfoAdapter
    private lateinit var bottomNavBar : BottomNavigationView

    val TAG = "RecipeSearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipesearch)

        setupRecyclerView()
        setupBottomNav()

        recipeInfoAdapter.setOnItemClickListener {
            val intent = Intent(this@RecipeSearchActivity, RecipeInfoActivity::class.java)
            intent.apply{
                putExtra("recipeInfo", it)
            }
            startActivity(intent)
        }

        val recipeRepo = RecipeSearchRepo()
        val viewModelProviderFactory = RecipeSearchProvider(recipeRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipeVM::class.java]

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

    private fun setupBottomNav(){
        bottomNavBar = findViewById(R.id.bottomNavBar)
        bottomNavBar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home -> startActivity(Intent(this, RecipeSearchActivity::class.java))
                R.id.fav -> startActivity(Intent(this, FavoritesActivity::class.java))
            }
            true
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