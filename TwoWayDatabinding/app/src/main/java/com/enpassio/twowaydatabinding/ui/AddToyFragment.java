package com.enpassio.twowaydatabinding.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;
import com.enpassio.twowaydatabinding.databinding.AddToyBinding;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;
import com.enpassio.twowaydatabinding.viewmodel.AddToyViewModel;
import com.enpassio.twowaydatabinding.viewmodel.AddToyViewModelFactory;

import static com.enpassio.twowaydatabinding.ui.ToyListFragment.CHOSEN_TOY;

public class AddToyFragment extends Fragment {

    private AddToyBinding binding;
    private AddToyViewModel mViewModel;
    private static final int NEW_TOY = -1;

    public AddToyFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_toy, container, false);

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ToyEntry chosenToy = null;

        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(CHOSEN_TOY)) {
            chosenToy = bundle.getParcelable(CHOSEN_TOY);
        }

        //Get the view model instance and pass it to the binding implementation
        AddToyViewModelFactory factory = new AddToyViewModelFactory(InjectorUtils.provideRepository(getContext()), chosenToy);
        mViewModel = ViewModelProviders.of(this, factory).get(AddToyViewModel.class);

        binding.setViewModel(mViewModel);

        binding.fab.setOnClickListener(v -> saveToy());
    }

    private void saveToy() {
        // Verify that toy name is not empty
        if (TextUtils.isEmpty(mViewModel.getToyBeingModified().getToyName())) {
            Toast.makeText(requireContext(), R.string.toy_empty_warning, Toast.LENGTH_SHORT).show();
            return;
        }
        mViewModel.saveToy();
        returnToListFragment();
    }

    /*This can be triggered either by up or both buttons. In both cases,
    we first need to check whether there are unsaved changes and warn the user if necessary*/
    public void onBackClicked() {
        if (mViewModel.isChanged()) {
            openAlertDialog();
        } else {
            returnToListFragment();
        }
    }

    private void openAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.unsaved_changes_warning_title))
                .setMessage(getString(R.string.unsaved_changes_warning_message))
                // Specifying a listener allows you to take an action before dismissing the dialog.
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                    // Continue with back operation
                    returnToListFragment();
                })
                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void returnToListFragment() {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.popBackStack();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This is for making up button in the toolbar behave like back button
        if (item.getItemId() == android.R.id.home) {
            onBackClicked();
        }
        return super.onOptionsItemSelected(item);
    }
}
