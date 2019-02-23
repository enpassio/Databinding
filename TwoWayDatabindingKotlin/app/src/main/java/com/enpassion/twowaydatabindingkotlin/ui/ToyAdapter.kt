package com.enpassion.twowaydatabindingkotlin.ui

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.databinding.ItemToyBinding


class ToyAdapter(private val mListener: ToyClickListener) :
    RecyclerView.Adapter<ToyAdapter.ToyViewHolder>() {

    var toyList: List<ToyEntry>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyViewHolder {
        val binding = DataBindingUtil
            .inflate<ItemToyBinding>(
                LayoutInflater.from(parent.context), R.layout.item_toy,
                parent, false
            )
        //Pass an item click listener to each item layout.
        binding.toyItemClick = mListener
        return ToyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToyViewHolder, position: Int) {
        //For each item, corresponding product object is passed to the binding
        holder.binding.toy = toyList?.get(position)
        //This is to force bindings to execute right away
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return toyList?.size ?: 0
    }

    inner class ToyViewHolder(val binding: ItemToyBinding) : RecyclerView.ViewHolder(binding.root)

    interface ToyClickListener {
        fun onToyClicked(toyId: Int)
    }

}