package com.example.recepiesapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recepiesapplication.CategoriesListAdapter.CategoryViewHolder
import models.Category

class CategoriesListAdapter :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories: List<Category> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    fun setCategories(categories: List<Category>) {
        val result = DiffUtil.calculateDiff(CategoryDiffUtilCallback(this.categories, categories))
        result.dispatchUpdatesTo(this)
        this.categories = categories
    }

    class CategoryViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = itemView.findViewById(R.id.image_category)
        private val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)
        fun bind(item: Category) {
            imageView.setImageDrawable(null)
            titleTextView.text = item.title
            descriptionTextView.text = item.description
        }
    }

    private class CategoryDiffUtilCallback(
        private var oldItems: List<Category>,
        private var newItems: List<Category>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].id == newItems[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }
}


/* class CategoriesListAdapter(private val context: Context, private val dataSet: List<Category>) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val category = dataSet[position]

    holder.titleTextView.text = category.title
    holder.descriptionTextView.text = category.description

    // Пробуем загрузить изображение из ассетов
    val imageDrawable = AssetUtils.loadImageDrawableFromAssets(context, category.imageUrl)
    if (imageDrawable != null) {
        holder.imageView.setImageDrawable(imageDrawable)
    } else {
        holder.imageView.setImageDrawable(null)
    }
}


    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_category)
        val titleTextView: TextView = itemView.findViewById(R.id.tv_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.tv_description)

        fun bind(category: Category) {
            imageView.setImageResource(category.imageUrl)
            titleTextView.text = category.title
            descriptionTextView.text = category.description
        }
    }
} */