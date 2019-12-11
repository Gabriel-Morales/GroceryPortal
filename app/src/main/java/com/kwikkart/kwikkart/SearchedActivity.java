package com.kwikkart.kwikkart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwikkart.kwikkart.model.Item;

import java.util.ArrayList;


public class SearchedActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView searchedRecycler;

    private String searchQuery;
    private ArrayList<Item> allDatabaseItems;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_activity);
        toolbar = findViewById(R.id.searchedToolbar);
        searchedRecycler = findViewById(R.id.searchedRecycler);
       setSupportActionBar(toolbar);

       getSupportActionBar().setHomeButtonEnabled(true);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String searchedItem = getIntent().getExtras().get("searched_item").toString();
        getSupportActionBar().setTitle("Results for " + searchedItem);
        allDatabaseItems = HomeFragment.allDatabaseItems;

        this.searchQuery = searchedItem;

        if (allDatabaseItems.isEmpty())
        {
            return;
        }

        pruneResults();

        initializeRecyler();

    }

    /**
     * onStart
     */
    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     * onOptionsItemSelected
     * @param item MenuItem
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * initializeRecyler
     */
    private void initializeRecyler()
    {
        SearchItemAdapter adapter = new SearchItemAdapter(allDatabaseItems.size(), allDatabaseItems);
        searchedRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setReverseLayout(false);
        searchedRecycler.setLayoutManager(layoutManager);

    }

    /**
     * pruneResults
     */
    private void pruneResults()
    {

        ArrayList<Item> temp  = new ArrayList<>();
        for (Item item: allDatabaseItems)
        {
            if (item.getName().contains(searchQuery) && !hasItemName(temp, item.getName()))
            {
                temp.add(item);
            }
        }

        allDatabaseItems = new ArrayList<>(temp);

    }

    /**
     * hasItemName
     * @param temp ArrayList<Item>
     * @param name String
     * @return boolean
     */
    //Remove duplicates by searching to see if the string is in the list already. Returns true if in string.
    private boolean hasItemName(ArrayList<Item> temp, String name)
    {
        for (Item item : temp)
        {
            if (item.getName().equals(name))
            {
                return true;
            }
        }

        return false;
    }

}
