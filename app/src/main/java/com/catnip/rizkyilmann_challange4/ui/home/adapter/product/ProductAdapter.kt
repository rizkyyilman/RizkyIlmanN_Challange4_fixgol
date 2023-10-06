package com.catnip.rizkyilmann_challange4.ui.home.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.catnip.heketon3.core.ViewHolderBinder
import com.catnip.rizkyilmann_challange4.databinding.ItemGridMenuBinding
import com.catnip.rizkyilmann_challange4.databinding.ItemLinearMenuBinding
import com.catnip.rizkyilmann_challange4.model.DetailMenu

class ProductAdapter (val adapterLayoutMode: AdapterLayoutMode,
                      private val onClickListener: (DetailMenu) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val dataDiffer = AsyncListDiffer(this,object : DiffUtil.ItemCallback<DetailMenu>() {
        override fun areItemsTheSame(oldItem: DetailMenu, newItem: DetailMenu): Boolean {
            return oldItem.position == newItem.position &&
                    oldItem.name == newItem.name &&
                    oldItem.price == newItem.price &&
                    oldItem.desc == newItem.desc &&
                    oldItem.imgUrl == newItem.imgUrl
        }

        override fun areContentsTheSame(oldItem: DetailMenu, newItem: DetailMenu): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  when (viewType){
            AdapterLayoutMode.GRID.ordinal -> {
                GridMenuItemViewHolder(
                    binding = ItemGridMenuBinding.inflate(
                        LayoutInflater.from(parent.context),parent,false
                    ),onClickListener
                )
            }

            else -> {
                LinearMenuItemViewHolder(
                    binding = ItemLinearMenuBinding.inflate(
                        LayoutInflater.from(parent.context),parent,false
                    ),onClickListener)
            }
        }
    }

    override fun getItemCount(): Int = dataDiffer.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderBinder<DetailMenu>).bind(dataDiffer.currentList[position])
    }

    fun submitData(data : List<DetailMenu>){
        dataDiffer.submitList(data)
    }

    fun refreshList() {
        notifyItemRangeChanged(0,dataDiffer.currentList.size)
    }

    override fun getItemViewType(position: Int): Int {
        return adapterLayoutMode.ordinal
    }
}
