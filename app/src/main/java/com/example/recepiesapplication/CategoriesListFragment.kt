package com.example.recepiesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recepiesapplication.databinding.FragmentListCategoriesBinding
import models.Category

class CategoriesListFragment : Fragment() {

    private var _binding: FragmentListCategoriesBinding? = null
    private val binding
        get() = _binding
            ?: throw IllegalStateException("Binding property accessed before onCreateView() or after onDestroyView()")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecycler() {
        val categoriesAdapter = CategoriesListAdapter(STUB.getCategories())
        binding.rvCategories.adapter = categoriesAdapter

        categoriesAdapter.setOnItemClickListener(object : CategoriesListAdapter.OnItemClickListener {
            override fun onItemClick(category: Category) {
                openRecipesByCategoryId(category.id)
            }
        })
    }

    private fun openRecipesByCategoryId(categoryId: Int) {
        val recipesListFragment = RecipesListFragment().apply {
            arguments = Bundle().apply {
                putString("categoryId", categoryId.toString())
            }
        }

        parentFragmentManager.beginTransaction().apply {
            replace(R.id.mainContainer, recipesListFragment)
            addToBackStack(null)
            commit()
        }
    }

}