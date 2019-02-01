package com.enpassio.twowaydatabinding.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.twowaydatabinding.R;
import com.enpassio.twowaydatabinding.data.ToyEntry;
import com.enpassio.twowaydatabinding.databinding.FragmentListBinding;
import com.enpassio.twowaydatabinding.utils.InjectorUtils;
import com.enpassio.twowaydatabinding.viewmodel.MainViewModel;

public class ToyListFragment extends Fragment implements ToyAdapter.ToyClickListener {

    private MainViewModel mViewModel;
    private ToyAdapter mAdapter;
    private static final String TAG = "ToyListFragment";
    private FragmentListBinding binding;

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

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get the view model instance and pass it to the binding implementation
        mViewModel = ViewModelProviders.of(getActivity(), InjectorUtils.provideMainListFactory(getActivity())).get(MainViewModel.class);
        binding.setViewModel(mViewModel);

        mViewModel.getToyList().observe(getActivity(), toyEntries -> {
            mViewModel.isLoading.set(false);
            if (toyEntries == null || toyEntries.isEmpty()) {
                mViewModel.isEmpty.set(true);
            } else {
                mViewModel.isEmpty.set(false);
                mAdapter.setToyList(toyEntries);
                binding.invalidateAll();
            }
        });
    }

    @Override
    public void onToyClicked(ToyEntry chosenToy) {
        mViewModel.setChosenToy(chosenToy);
        //TODO: open AddToyFragment
    }
}
