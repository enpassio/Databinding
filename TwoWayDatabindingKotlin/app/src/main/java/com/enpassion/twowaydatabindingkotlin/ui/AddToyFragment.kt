package com.enpassion.twowaydatabindingkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.databinding.AddToyBinding
import com.enpassion.twowaydatabindingkotlin.utils.provideRepository
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModel
import com.enpassion.twowaydatabindingkotlin.viewmodel.AddToyViewModelFactory

class AddToyFragment : androidx.fragment.app.Fragment() {

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
        val chosenToy : ToyEntry? = arguments?.getParcelable(CHOSEN_TOY)

        //Get the view model instance and pass it to the binding implementation
        val factory = AddToyViewModelFactory(provideRepository(requireContext()), chosenToy)
        mViewModel = ViewModelProviders.of(this, factory).get(AddToyViewModel::class.java)

        binding.viewModel = mViewModel

        binding.fab.setOnClickListener {
            saveToy()
        }
    }

    private fun saveToy() {
        // Check if toy name is not empty
        if(mViewModel.toyBeingModified.toyName.isNullOrBlank()){
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