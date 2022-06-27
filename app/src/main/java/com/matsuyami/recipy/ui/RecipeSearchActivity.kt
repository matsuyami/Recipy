package com.matsuyami.recipy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matsuyami.recipy.R
import com.matsuyami.recipy.ui.fragments.Favorites
import com.matsuyami.recipy.ui.fragments.Search

class RecipeSearchActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private lateinit var bottomNavBar : BottomNavigationView

    val TAG = "RecipeSearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipesearch)

        setupBottomNav()

//        recipeInfoAdapter.setOnItemClickListener {
//            val intent = Intent(this@RecipeSearchActivity, RecipeInfoFragment::class.java)
//            intent.apply{
//                putExtra("recipeInfo", it)
//            }
//            startActivity(intent)
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        setupSearchView(menuItem)

        return super.onCreateOptionsMenu(menu)
    }


    private fun setupBottomNav(){
        bottomNavBar = findViewById(R.id.bottomNavBar)
        bottomNavBar.setOnItemSelectedListener{
            when(it.itemId){
                R.id.home -> startActivity(Intent(this, RecipeSearchActivity::class.java))
                R.id.fav -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_recipe_search, Favorites())
                        .commit()
                }
            }
            true
        }
    }


    private fun setupSearchView(menuItem : MenuItem?){
        searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Search an ingredient..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrBlank()) {
                    val searchFrag = Search()
                    val bundle = Bundle()
                    bundle.putString("query", query)
                    searchFrag.arguments = bundle
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment_recipe_search, searchFrag)
                        commit()
                    }
                }
                return false
            }
        })
    }

}