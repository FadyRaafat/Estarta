package com.fady.estarta.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.fady.estarta.R
import com.fady.estarta.data.models.Result
import com.fady.estarta.databinding.ItemProductBinding
import com.fady.estarta.utils.base.DataBoundListAdapter

class ProductsAdapter(
    var onclick: (Result) -> Unit
) :
    DataBoundListAdapter<Result, ItemProductBinding>(
        diffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areContentsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(
                oldItem: Result,
                newItem: Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    lateinit var context: Context
    override fun createBinding(parent: ViewGroup): ItemProductBinding {
        context = parent.context
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product,
            parent,
            false
        )
    }

    override fun bind(
        binding: ItemProductBinding,
        item: Result,
        position: Int,
        adapterPosition: Int
    ) {
        binding.item = item
        binding.root.setOnClickListener {
            onclick(item)
        }

    }


}