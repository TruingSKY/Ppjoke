package com.example.ppjoke.ui.find;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.libnavannotation.FragmentDestination;
import com.example.ppjoke.databinding.FragmentHomeBinding;
import com.example.ppjoke.ui.home.HomeViewModel;
import com.example.ppjoke.ui.sofa.SofaFragment;


@FragmentDestination(pageUrl = "main/tabs/find")
public class FindFragment extends SofaFragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}