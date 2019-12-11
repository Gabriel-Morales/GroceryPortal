package com.kwikkart.kwikkart;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.SearchView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
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
    private SearchView searchView;


    private FirebaseRecyclerAdapter<Item, ViewHolder> mAdapter;
    private FirebaseRecyclerOptions<Item> options;
    private Query query;
    private String queryString;
    public static ArrayList<Item> allDatabaseItems;
    private static ArrayList<Item> itemsInCart  = new ArrayList<>();

    /**
     * HomeFragment
     */
    public HomeFragment()
    {
        //Default constructor.
        this.queryString = "all_items";
    }

    /**
     * onCreateView
     * @param inflater @NonNull LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_home, container, false);
        allDatabaseItems = new ArrayList<>();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        recycleView = view.findViewById(R.id.storelist);
        tabs = view.findViewById(R.id.tabs);
        searchView = view.findViewById(R.id.search);
        progressBar = getActivity().findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Intent intent = new Intent(getActivity(), SearchedActivity.class);
                intent.putExtra("searched_item", query);
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return view;
    }

    /**
     * onStart
     */
    @Override
    public void onStart() {
        super.onStart();
        initializeTabs();
        initializePrimaryRecycler();
        mAdapter.startListening();
    }

    /**
     * onStop
     */
    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    /**
     * initializePrimaryRecycler
     */
    private void initializePrimaryRecycler()
    {
        mAdapter = initializeAdapter(queryString);
        recycleView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(false);
        recycleView.setLayoutManager(layoutManager);
    }

    /**
     * initializeAdapter
     * @param queryString String
     * @return FirebaseRecyclerAdapter<Item, ViewHolder>
     */
    private FirebaseRecyclerAdapter<Item, ViewHolder> initializeAdapter(String queryString)
    {

        query = FirebaseDatabase.getInstance().getReference().child(queryString);

        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(query, Item.class).build();

        FirebaseRecyclerAdapter<Item, ViewHolder> mAdapter = new FirebaseRecyclerAdapter<Item, ViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Item model) {

                holder.initializeView(model.getName(), model.getPrice(), model.getImage(), model);
                allDatabaseItems.add(model);
            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
                progressBar.setVisibility(View.GONE);

                return new ViewHolder(view);
            }

            @Override
            public void updateOptions(@NonNull FirebaseRecyclerOptions<Item> options) {
                super.updateOptions(options);
                allDatabaseItems = new ArrayList<>();
            }

        };

        return mAdapter;

    }

    /**
     * initializeTabs
     */
    private void initializeTabs()
    {

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                switch (position)
                {
                    case 0:
                        updateQuery("all_items");
                        break;
                    case 1:
                        updateQuery("Meat");
                        break;
                    case 2:
                        updateQuery("Eggs_Dairy");
                        break;
                    case 3:
                        updateQuery("Produce");
                        break;
                    case 4:
                       updateQuery("Drinks");
                        break;
                    case 5:
                        updateQuery("Dessert");
                        break;
                    case 6:
                        updateQuery("Other");
                        break;
                    default:
                        break;
                }

                options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(query, Item.class).build();
                mAdapter.updateOptions(options);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    /**
     * createFragment
     * @param destFragment Fragment
     */
    private void createFragment(Fragment destFragment)
    {

        if (destFragment == null)
        {
            return;
        }

        getFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, destFragment).commit();

    }

    /**
     * addItemToCart
     * @param item Item
     */
    public static void addItemToCart(Item item)
    {
        //Provides a "deep" copy of the item. A less efficient version, but it's a small object.
        Item copyItem = new Item(item.getName(), item.getPrice(), item.getImage());
        itemsInCart.add(copyItem);
    }

    /**
     * getCart
     * @return ArrayList<Item>
     */
    public static ArrayList<Item> getCart()
    {
        return itemsInCart;
    }

    /**
     * clearCart
     */
    public static void clearCart()
    {
        if (itemsInCart == null)
        {
            return;
        }

        itemsInCart = null;
        itemsInCart = new ArrayList<>();
    }

    /**
     * updateQuery
     * @param queryString String
     */
    public void updateQuery(String queryString)
    {
        this.queryString = queryString;
        query = FirebaseDatabase.getInstance().getReference().child(queryString);
    }

}