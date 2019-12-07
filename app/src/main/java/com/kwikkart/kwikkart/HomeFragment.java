package com.kwikkart.kwikkart;


import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kwikkart.kwikkart.model.Item;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recycleView;
    private View view;
    private TabLayout tabs;
    private ProgressBar progressBar;

    private FirebaseRecyclerAdapter<Item, ViewHolder> mAdapter;
    private FirebaseRecyclerOptions<Item> options;
    private Query query;
    private String queryString;
    private int position;
    private int scrollPos;
    private static ArrayList<Item> itemsInCart  = new ArrayList<>();

    public HomeFragment()
    {
        //Default constructor.
        this.queryString = "all_items";
    }


    public HomeFragment(String queryString, int position, int scrollPos)
    {
        this.queryString = queryString;
        this.position = position;
        this.scrollPos = scrollPos;
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);

        recycleView = view.findViewById(R.id.storelist);
        tabs = view.findViewById(R.id.tabs);
        progressBar = getActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        tabs.setVisibility(View.INVISIBLE);
        initializeTabs();
        initializePrimaryRecycler();

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }


    private void initializePrimaryRecycler()
    {

        mAdapter = initializeAdapter(queryString);

        recycleView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(false);
        recycleView.setLayoutManager(layoutManager);
    }


    private FirebaseRecyclerAdapter<Item, ViewHolder> initializeAdapter(String queryString)
    {

        query = FirebaseDatabase.getInstance().getReference().child(queryString);

        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(query, Item.class).build();

        FirebaseRecyclerAdapter<Item, ViewHolder> mAdapter = new FirebaseRecyclerAdapter<Item, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Item model) {

                holder.initializeView(model.getName(), model.getPrice(), model.getImage(), model);

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
                progressBar.setVisibility(View.GONE);
                return new ViewHolder(view);
            }

        };

        return mAdapter;

    }

    private void initializeTabs()
    {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                tabs.setScrollX(scrollPos);
                tabs.setVisibility(View.VISIBLE);

            }
        }, 0);


        TabLayout.Tab selectedTab = tabs.getTabAt(position);
        selectedTab.select();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                mAdapter.stopListening();
                int position = tab.getPosition();
                Fragment destFragment = null;

                switch (position)
                {
                    case 0:
                        destFragment = new HomeFragment("all_items", position, tabs.getScrollX());
                        break;
                    case 1:
                        destFragment = new HomeFragment("Meat", position, tabs.getScrollX());
                        break;
                    case 2:
                        destFragment = new HomeFragment("Eggs_Dairy", position, tabs.getScrollX());
                        break;
                    case 3:
                        destFragment = new HomeFragment("Produce", position, tabs.getScrollX());
                        break;
                    case 4:
                        destFragment = new HomeFragment("Drinks", position, tabs.getScrollX());
                        break;
                    case 5:
                        destFragment = new HomeFragment("Dessert", position, tabs.getScrollX());
                        break;
                    case 6:
                        destFragment = new HomeFragment("Other", position, tabs.getScrollX());
                        break;
                    default:
                        break;
                }

                createFragment(destFragment);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void createFragment(Fragment destFragment)
    {

        if (destFragment == null)
        {
            return;
        }

        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, destFragment).commit();

    }

    public static void addItemToCart(Item item)
    {
        itemsInCart.add(item);
    }

    public static ArrayList<Item> getCart()
    {
        return itemsInCart;
    }

    public static void clearCart()
    {
        if (itemsInCart == null)
        {
            return;
        }

        itemsInCart = null;
        itemsInCart = new ArrayList<>();
    }

}