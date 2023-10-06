package com.catnip.rizkyilmann_challange4.ui.home.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.catnip.rizkyilmann_challange4.databinding.ItemCategoryLinearBinding
import com.catnip.rizkyilmann_challange4.model.Category

class CategoryAdapter (private val onItemClick: (Category) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryItemViewHolder>() {
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    })

    fun submitData(data: List<Category>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding =
            ItemCategoryLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener { onItemClick(category) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    class CategoryItemViewHolder(private val binding: ItemCategoryLinearBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.tvCategoryName.text = category.name
            binding.ivCategoryIcon.load(category.categoryImgUrl)
        }
    }
}