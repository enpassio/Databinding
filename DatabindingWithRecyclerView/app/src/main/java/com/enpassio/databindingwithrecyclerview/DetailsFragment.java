package com.enpassio.databindingwithrecyclerview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.enpassio.databindingwithrecyclerview.databinding.FragmentDetailsBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import static com.enpassio.databindingwithrecyclerview.ProductListFragment.PRODUCT_KEY;

public class DetailsFragment extends Fragment {

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentDetailsBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false);

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);

        setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Product chosenProduct = bundle.getParcelable(PRODUCT_KEY);
            binding.setProduct(chosenProduct);
        }

        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }
}
