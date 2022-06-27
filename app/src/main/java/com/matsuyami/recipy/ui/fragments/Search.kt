package com.matsuyami.recipy.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.matsuyami.recipy.R
import com.matsuyami.recipy.adapters.RecipeInfoAdapter
import com.matsuyami.recipy.data.repositories.RecipeSearchRepo
import com.matsuyami.recipy.ui.RecipeSearchProvider
import com.matsuyami.recipy.utils.Resource
import com.matsuyami.recipy.viewmodels.RecipeVM

class Search : Fragment() {
    private lateinit var rvRecipeInfo : RecyclerView
    private lateinit var recipeInfoAdapter : RecipeInfoAdapter
    private lateinit var viewModel: RecipeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvRecipeInfo = view.findViewById(R.id.rvRecipeInfo)

        val queryString = arguments?.getString("query")
        setupRecyclerView()

        recipeInfoAdapter.setOnItemClickListener {
            val bundle = Bundle()
            bundle.putSerializable("recipeInfo", it)
            val recipeInfo = RecipeInfo()
            recipeInfo.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_recipe_search, recipeInfo)
                commit()
            }
        }

        val recipeRepo = RecipeSearchRepo()
        val viewModelProviderFactory = RecipeSearchProvider(recipeRepo)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)[RecipeVM::class.java]

        viewModel.recipes.observe(viewLifecycleOwner, Observer{response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let{ recipeResp ->
                        Log.d("Search", recipeResp.toString())
                        recipeInfoAdapter.differ.submitList(recipeResp.results.filter{
                            it.description != null &&
                            it.description.isNotEmpty() &&
                            it.totalTimeTier != null
                        })
                    }
                }
                is Resource.Error ->{
                    response.message?.let { msg ->
                        Log.e("SearchFragment", "An error occurred: $msg")
                    }
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

        viewModel.getRecipes(queryString!!)
    }


    private fun hideProgressBar(){
    }

    private fun showProgressBar(){
    }

    private fun setupRecyclerView(){
        recipeInfoAdapter = RecipeInfoAdapter()

        rvRecipeInfo.apply {
            adapter = recipeInfoAdapter
            layoutManager = FlexboxLayoutManager(requireContext()).apply{
            justifyContent = JustifyContent.SPACE_EVENLY
            alignItems= AlignItems.CENTER
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
            }
        }
    }
}