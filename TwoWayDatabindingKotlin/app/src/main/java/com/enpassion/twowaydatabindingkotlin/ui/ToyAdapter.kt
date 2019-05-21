package com.enpassion.twowaydatabindingkotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyViewHolder = ToyViewHolder.from(parent)

    override fun onBindViewHolder(holder: ToyViewHolder, position: Int) = holder.bind(toyList?.get(position), mListener)

    override fun getItemCount(): Int {
        //If list is null, return 0, otherwise return the size of the list
        return toyList?.size ?: 0
    }

    class ToyViewHolder(private val binding: ItemToyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(currentToy: ToyEntry?, clickListener : ToyClickListener){
            binding.toy = currentToy
            binding.toyItemClick = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ToyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DataBindingUtil
                    .inflate<ItemToyBinding>(layoutInflater, R.layout.item_toy,
                        parent, false)
                return ToyViewHolder(binding)
            }
        }
    }

    interface ToyClickListener {
        fun onToyClicked(chosenToy: ToyEntry)
    }

}