package com.example.art_stationary.Fragments;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.art_stationary.Adapter.ExpandAdapter;
import com.example.art_stationary.Adapter.ThreeCatAdapter;
import com.example.art_stationary.Model.ExpandedCategroryModel;
import com.example.art_stationary.Model.ParentCategoryModel;
import com.example.art_stationary.Model.Recyclerhomemodel;
import com.example.art_stationary.Model.SubCatModel;
import com.example.art_stationary.Model.SubCategoryModel;
import com.example.art_stationary.Model.ThreeCatModel;
import com.example.art_stationary.R;
import com.example.art_stationary.Retrofit.RetrofitService;
import com.example.art_stationary.Retrofit.ServiceResponse;
import com.example.art_stationary.Retrofit.ServiceUrls;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CategoriesFragment extends Fragment implements ServiceResponse {

    //Expandable List

    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    ConstraintLayout img_back;
    RecyclerView categoryRecyler;
    ExpandableListView expandableList;
    ThreeCatAdapter threeLevelListAdapterAdapter;

    private final long Numberr = 9876543210L;

    ArrayList<ExpandedCategroryModel>  parentCategoryList;
    ArrayList<SubCategoryModel>  subCategoryList;

    List<String[]> secondLevel = new ArrayList<>();

    String[] movies = new String[]{"Ink Toner", "Paper", "Presentation", "Envelop"};

    String[] games = new String[]{};

    /**
     * The Horror movie list.
     */
    // movies category has further genres
    String[] horror = new String[]{};
    /**
     * The Action Movies List.
     */
    String[] action = new String[]{};
    /**
     * The Thriller Movies List.
     */
    String[] thriller = new String[]{};

    String[] envelop = new String[]{"Envelop", "Envelop", "Envelop"};

    LinkedHashMap<String, String[]> thirdLevelMovies = new LinkedHashMap<>();
    /**
     * Datastructure for Third level games.
     */
    LinkedHashMap<String, String[]> thirdLevelGames = new LinkedHashMap<>();
    /**
     * The Second level.
     */


    /**
     * The Data.
     */
    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_categories, container, false);
        getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.black));
        img_back =view.findViewById(R.id.img_back);
        navBar = getActivity().findViewById(R.id.bottomNavigationView);
        navBar.setVisibility(View.VISIBLE);
        toolbar = view.findViewById(R.id.toolbar);
        tooltext = toolbar.findViewById(R.id.toolheadtext);
        tooltext.setText("Categories");
        categoryRecyler = view.findViewById(R.id.categoryRecyler);
        expandableList = view.findViewById(R.id.expandableList);
        parentCategoryList = new ArrayList<>();
        subCategoryList = new ArrayList<>();

        getCategories();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        // second level category names (genres)
        //secondLevel.add(movies);
       // secondLevel.add(games);
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelMovies.put(movies[0], horror);
        thirdLevelMovies.put(movies[1], action);
        thirdLevelMovies.put(movies[2], thriller);
        thirdLevelMovies.put(movies[3], envelop);
        // all data
        data.add(thirdLevelMovies);
        data.add(thirdLevelGames);
        //data.add(thirdLevelSerials);


        // expandable listview

        // parent adapter
        threeLevelListAdapterAdapter = new ThreeCatAdapter(getActivity(), parentCategoryList, data);
        expandableList.setAdapter( threeLevelListAdapterAdapter );

        // OPTIONAL : Show one list at a time
        expandableList.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableList.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        return view;
    }


    private void getCategories() {
        new RetrofitService(getContext(), ServiceUrls.GETCATEGORIES,
                1, 1, this).callService(true);
    }

    @Override
    public void onServiceResponse(String result, int requestCode, int resCode) {
        if (requestCode == 1) {
            try {
                if (resCode==200) {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONObject output = jsonObject.getJSONObject("output");
                    Log.d(TAG, "Category Response: Outpuut Response---"+output.toString());
                    JSONArray parentCategory =  output.getJSONArray("data");
                    Log.d(TAG, "all data Response: Outpuut Response---"+parentCategory.toString());
                    for (int i = 0; i < parentCategory.length(); i++) {
                        JSONObject data = parentCategory.optJSONObject(i);
                        ExpandedCategroryModel expandedCategroryModel = new ExpandedCategroryModel();
                        expandedCategroryModel.setTitle(data.optString("title"));
//                        if (!data.isNull("subcategories")){
//                            JSONArray subCategory =  data.optJSONArray("subcategories");
//                            for (int j =0; j < subCategory.length(); j++){
//                                JSONObject subdata = subCategory.optJSONObject(j);
//                                SubCategoryModel subCategoryModel = new SubCategoryModel();
//                                subCategoryModel.setTitle(subdata.optString("title"));
//                                subCategoryList.add(subCategoryModel);
//                            }
//                            expandedCategroryModel.setSubcategories(subCategoryList);
//                        }
                        parentCategoryList.add(expandedCategroryModel);
                    }
                    threeLevelListAdapterAdapter.notifyDataSetChanged();
                }
            }catch (Exception e){
                Log.d(TAG, "onServiceResponse: API Error---"+e.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onServiceError(String error, int requestCode, int resCode) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }
}