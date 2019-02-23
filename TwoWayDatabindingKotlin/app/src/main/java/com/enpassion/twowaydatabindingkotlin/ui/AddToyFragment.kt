package com.enpassion.twowaydatabindingkotlin.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.databinding.AddToyBinding
import com.enpassion.twowaydatabindingkotlin.utils.provideRepository
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModel
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModelFactory

const val NEW_TOY = -1

class AddToyFragment : Fragment() {

    private lateinit var binding: AddToyBinding

    init {
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_toy, container, false
        )
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //If there is no id specified in the arguments, then it should be a new toy
        val toyId = arguments?.getInt(TOY_ID) ?: NEW_TOY

        //Get the view model instance and pass it to the binding implementation
        val factory = AddToyViewModelFactory(provideRepository(requireContext()), toyId)
        val viewModel = ViewModelProviders.of(this, factory).get(AddToyViewModel::class.java)

        binding.viewModel = viewModel

        if (toyId >= 0) { //Edit case
            viewModel.chosenToy?.observe(this, Observer { toyEntry ->
                toyEntry?.let {
                    viewModel.toyBeingModified = it
                    binding.invalidateAll() }
            })
        }

        binding.fab.setOnClickListener {
            viewModel.saveToy()
            fragmentManager?.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //This is for making up button in the toolbar behave like back button
        if (item.itemId == android.R.id.home) {
            fragmentManager?.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }
}