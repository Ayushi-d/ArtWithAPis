package com.example.art_stationary.Fragments.SubCategoriesViewFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.CategoriesAdapter.ViewproductCategoryAdapter.ViewproductCategoryAdapter;
import com.example.art_stationary.Fragments.SubCategoriesViewFragment.Presenter.SubCategoriesViewFragmentPresenter;
import com.example.art_stationary.R;

import org.jetbrains.annotations.Nullable;


public class SubCategoriesViewFragment extends Fragment {
    private RecyclerView ViewproductRecylerView;
    private View view;
    private ViewproductCategoryAdapter viewproductCategoryAdapter;
    private SubCategoriesViewFragmentPresenter subCategoriesViewFragmentPresenter;

    public static SubCategoriesViewFragment newInstance() {
        return new SubCategoriesViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_sub_categories_view, container, false);

        initViews(view);
        return view;
    }


    private void initViews(View view) {
        TextView textView = view.findViewById(R.id.commonTextView);
        String value = String.valueOf(getArguments().getInt("position"));
        textView.setText("id :  " + value);
        ViewproductRecylerView = view.findViewById(R.id.ViewproductRecylerView);



        subCategoriesViewFragmentPresenter = new SubCategoriesViewFragmentPresenter(getActivity(),ViewproductRecylerView);

        subCategoriesViewFragmentPresenter.getcategories(value);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    // pause function call
    @Override
    public void onPause() {
        super.onPause();
    }

    // resume function call
    @Override
    public void onResume() {
        super.onResume();
    }

    // stop when we close
    @Override
    public void onStop() {
        super.onStop();
    }

    // destroy the view
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}