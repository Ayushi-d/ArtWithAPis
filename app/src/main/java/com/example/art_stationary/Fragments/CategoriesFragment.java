package com.example.art_stationary.Fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.art_stationary.Adapter.ExpandAdapter;
import com.example.art_stationary.Adapter.ThreeCatAdapter;
import com.example.art_stationary.Model.ExpandedCategroryModel;
import com.example.art_stationary.Model.ParentCategoryModel;
import com.example.art_stationary.Model.SubCatModel;
import com.example.art_stationary.Model.SubCategoryModel;
import com.example.art_stationary.Model.ThreeCatModel;
import com.example.art_stationary.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CategoriesFragment extends Fragment {

    //Expandable List

    ConstraintLayout toolbar;
    TextView tooltext;
    BottomNavigationView navBar;
    ConstraintLayout img_back;
    RecyclerView categoryRecyler;
    ExpandableListView expandableList;

    String[] parent = new String[]{"Office Supplies", "Office Supplies"};

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


    /**
     * The Fps games.
     */
    // games category has further genres
    String[] fps = new String[]{"CS: GO", "Team Fortress 2", "Overwatch", "Battlefield 1", "Halo II", "Warframe"};
    /**
     * The Moba games.
     */
    String[] moba = new String[]{"Dota 2", "League of Legends", "Smite", "Strife", "Heroes of the Storm"};
    /**
     * The Rpg games.
     */
    String[] rpg = new String[]{"Witcher III", "Skyrim", "Warcraft", "Mass Effect II", "Diablo", "Dark Souls", "Last of Us"};
    /**
     * The Racing games.
     */
    String[] racing = new String[]{"NFS: Most Wanted", "Forza Motorsport 3", "EA: F1 2016", "Project Cars"};


    LinkedHashMap<String, String[]> thirdLevelMovies = new LinkedHashMap<>();
    /**
     * Datastructure for Third level games.
     */
    LinkedHashMap<String, String[]> thirdLevelGames = new LinkedHashMap<>();

    /**
     * Datastructure for Third level Serials.
     */
    // LinkedHashMap<String, String[]> thirdLevelSerials = new LinkedHashMap<>();


    /**
     * The Second level.
     */
    List<String[]> secondLevel = new ArrayList<>();


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

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        // second level category names (genres)
        secondLevel.add(movies);
        secondLevel.add(games);
        // secondLevel.add(serials);

        // movies category all data
        thirdLevelMovies.put(movies[0], horror);
        thirdLevelMovies.put(movies[1], action);
        thirdLevelMovies.put(movies[2], thriller);
        thirdLevelMovies.put(movies[3], envelop);


        // games category all data
//        thirdLevelGames.put(games[0], fps);
//        thirdLevelGames.put(games[1], moba);
//        thirdLevelGames.put(games[2], rpg);
//        thirdLevelGames.put(games[3], racing);




        // all data
        data.add(thirdLevelMovies);
        data.add(thirdLevelGames);
        //data.add(thirdLevelSerials);


        // expandable listview

        // parent adapter
        ThreeCatAdapter threeLevelListAdapterAdapter = new ThreeCatAdapter(getActivity(), parent, secondLevel, data);


        // set adapter
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
}