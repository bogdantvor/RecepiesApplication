package com.example.recepiesapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.recepiesapplication.databinding.FragmentListCategoriesBinding
import models.ARG_CATEGORY_ID
import models.ARG_CATEGORY_IMAGE_URL
import models.ARG_CATEGORY_NAME

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
            override fun onItemClick(categoryId: Int) {
                openRecipesByCategoryId(categoryId)
            }
        })
    }

    private fun openRecipesByCategoryId(categoryId: Int) {
        val category = STUB.getCategoryById(categoryId)
        val categoryName = category?.title ?: ""
        val categoryImageUrl = category?.imageUrl ?: ""

        val bundle = Bundle().apply {
            putInt(ARG_CATEGORY_ID, categoryId)
            putString(ARG_CATEGORY_NAME, categoryName)
            putString(ARG_CATEGORY_IMAGE_URL, categoryImageUrl)
        }

        val recipesListFragment = RecipesListFragment().apply {
            arguments = bundle
        }

        parentFragmentManager.commit {
            replace(R.id.mainContainer, recipesListFragment)
            addToBackStack(null)
        }
    }


}