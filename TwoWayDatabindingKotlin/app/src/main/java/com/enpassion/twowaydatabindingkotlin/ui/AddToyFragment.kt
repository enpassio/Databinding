package com.enpassion.twowaydatabindingkotlin.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.databinding.AddToyBinding
import com.enpassion.twowaydatabindingkotlin.utils.provideRepository
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModel
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModelFactory


const val NEW_TOY = -1

class AddToyFragment : Fragment() {

    private lateinit var binding: AddToyBinding
    private lateinit var mViewModel : AddToyViewModel

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
        mViewModel = ViewModelProviders.of(this, factory).get(AddToyViewModel::class.java)

        binding.viewModel = mViewModel

        if (toyId >= 0) { //Edit case
            val chosenToy = mViewModel.chosenToy
            chosenToy?.observe(this, Observer { toyEntry ->
                toyEntry?.let {
                    mViewModel.toyBeingModified = it
                    binding.invalidateAll()
                    chosenToy.removeObservers(this)
                }
            })
        }

        binding.fab.setOnClickListener {
            saveToy()
        }
    }

    private fun saveToy() {
        // Check if toy name is not empty
        if(mViewModel.toyBeingModified?.toyName.isNullOrBlank()){
            Toast.makeText(requireContext(), R.string.toy_empty_warning, Toast.LENGTH_SHORT).show()
            return
        }
        mViewModel.saveToy()
        fragmentManager?.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //If up button is clicked, check for changes before popping the fragment off
        if (item.itemId == android.R.id.home) {
            onBackClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    /*This can be triggered either by up or both buttons. In both cases,
    we first need to check whether there are unsaved changes and warn the user if necessary*/
    fun onBackClicked(){
        if(mViewModel.isChanged){
            openAlertDialog()
        } else {
            fragmentManager?.popBackStack()
        }
    }

    companion object {
        const val TAG = "AddToyFragment"
    }

    private fun openAlertDialog(){
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.unsaved_changes_warning_title))
            .setMessage(getString(R.string.unsaved_changes_warning_message))
            // Specifying a listener allows you to take an action before dismissing the dialog.
            // The dialog is automatically dismissed when a dialog button is clicked.
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                // Continue with back operation
                fragmentManager?.popBackStack()
            }
            // A null listener allows the button to dismiss the dialog and take no further action.
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

}