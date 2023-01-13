package com.fady.estarta.utils.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fady.estarta.R
import com.fady.estarta.databinding.NetworkStateItemBinding
import com.fady.estarta.presentation.activities.MainActivity
import com.fady.estarta.utils.common.NetworkState
import com.fady.estarta.utils.common.SingleLiveEvent

/**
 * A generic paging RecyclerView adapter that uses Data Binding & DiffUtil.
 *
 * @param <T> Type of the items in the list
 * @param <V> The type of the ViewDataBinding
</V></T> */
abstract class BaseBindingPagingAdapter<T, V : ViewDataBinding>(
    var context: Context,
    var networkStatusMutableLiveData: SingleLiveEvent<NetworkState>,
    var itemLayoutResource: Int,
    var retryPaging: () -> Unit = {},
    var onChangeState: (networkState: NetworkState) -> Unit = {},
    var onItemClick: (item: T) -> Unit = {},
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, RecyclerView.ViewHolder>(diffCallback) {

    private var networkState: NetworkState? = null

    init {
        networkStatusMutableLiveData.observe(context as MainActivity) { newNetworkState ->
            //called this line to handle init loading
            onChangeState.invoke(newNetworkState)
            val previousState = networkState
            val hadExtraRow = hasExtraRow()

            networkState = newNetworkState
            val hasExtraRow = hasExtraRow()

            if (hadExtraRow != hasExtraRow) {
                if (hadExtraRow) {
                    notifyItemRemoved(itemCount)
                } else {
                    notifyItemInserted(itemCount)
                }
            } else if (hasExtraRow && previousState != newNetworkState) {
                notifyItemChanged(itemCount - 1)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.network_state_item) {
            val binding: NetworkStateItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.network_state_item,
                viewGroup,
                false
            )
            NetworkStatusViewHolder(binding)
        } else {
            val binding: V = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                itemLayoutResource,
                viewGroup,
                false
            )
            ItemViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, i: Int) {
        if (getItemViewType(i) == R.layout.network_state_item)
            (holder as BaseBindingPagingAdapter<*, *>.NetworkStatusViewHolder).bindTo(networkState!!)
        else
            (holder as BaseBindingPagingAdapter<*, *>.ItemViewHolder).bindTo()

    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            position
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    internal inner class ItemViewHolder(val binding: V) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo() {
            if (getItem(adapterPosition) != null) {
                bind(binding, getItem(adapterPosition)!!, adapterPosition)
                binding.executePendingBindings()
                itemView.tag = getItem(adapterPosition).toString()
                binding.root.setOnClickListener { onItemClick.invoke(getItem(adapterPosition)!!) }
            }
        }
    }

    protected abstract fun bind(binding: V, item: T, adapterPosition: Int)

    internal inner class NetworkStatusViewHolder(var binding: NetworkStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(networkState: NetworkState) {
            binding.state = networkState
            binding.retry.setOnClickListener {
                retryPaging.invoke()
            }
        }
    }

}