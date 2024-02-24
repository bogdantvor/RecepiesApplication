package com.example.recepiesapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recepiesapplication.databinding.FragmentListCategoriesBinding

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}