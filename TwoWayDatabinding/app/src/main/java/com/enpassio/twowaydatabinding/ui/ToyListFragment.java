package com.enpassio.twowaydatabinding.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.model.ToyEntry;
import com.enpassio.twowaydatabinding.data.model.UIState;
import com.enpassio.twowaydatabinding.databinding.FragmentListBinding;
import com.enpassio.twowaydatabinding.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ToyListFragment extends Fragment implements ToyAdapter.ToyClickListener {

    private MainViewModel mViewModel;
    private ToyAdapter mAdapter;
    private static final String TAG = "ToyListFragment";
    private FragmentListBinding binding;
    private List<ToyEntry> mToyList;
    public static final String CHOSEN_TOY = "chosenToy";

    public ToyListFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_list, container, false);

        //Set adapter, divider and default animator to the recycler view
        mAdapter = new ToyAdapter(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(),
                LinearLayoutManager.VERTICAL);
        binding.recycler.addItemDecoration(dividerItemDecoration);
        binding.recycler.setItemAnimator(new DefaultItemAnimator());
        binding.recycler.setAdapter(mAdapter);

        //When fab clicked, open AddToyFragment
        binding.fab.setOnClickListener(v -> openAddToyFrag(new AddToyFragment()));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        //Get the view model instance and pass it to the binding implementation
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel.class);
        binding.setUiState(mViewModel.uiState);

        //Claim list of toys from view model
        mViewModel.getToyList().observe(this, toyEntries -> {
            if (toyEntries == null || toyEntries.isEmpty()) {
                mViewModel.uiState.set(UIState.EMPTY);
            } else {
                mViewModel.uiState.set(UIState.HAS_DATA);
                mAdapter.setToyList(toyEntries);
                mToyList = toyEntries;
            }
        });

        //Attach an ItemTouchHelper for swipe-to-delete functionality
        final CoordinatorLayout coordinator = requireActivity().findViewById(R.id.main_coordinator);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                //First take a backup of the toy to erase
                final ToyEntry toyToErase = mToyList.get(position);

                //Then delete the toy
                mViewModel.deleteToy(toyToErase);

                //Show a snack bar for undoing delete
                Snackbar snackbar = Snackbar
                        .make(coordinator, R.string.toy_is_deleted, Snackbar.LENGTH_LONG)
                        //If user clicks undo, reinsert backed-up toy
                        .setAction(R.string.undo, view -> mViewModel.insertToy(toyToErase));
                snackbar.show();

            }
        }).attachToRecyclerView(binding.recycler);
    }

    @Override
    public void onToyClicked(ToyEntry chosenToy) {
        //Pass chosen toy id to the AddToyFragment
        Bundle args = new Bundle();
        args.putParcelable(CHOSEN_TOY, chosenToy);
        AddToyFragment frag = new AddToyFragment();
        frag.setArguments(args);

        //Open AddToyFragment in edit form
        openAddToyFrag(frag);
    }

    private void openAddToyFrag(AddToyFragment frag) {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.beginTransaction()
                    .replace(R.id.main_container, frag)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
