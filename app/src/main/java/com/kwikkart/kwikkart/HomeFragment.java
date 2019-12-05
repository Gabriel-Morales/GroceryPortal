package com.kwikkart.kwikkart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kwikkart.kwikkart.model.Item;

public class HomeFragment extends Fragment {

    private RecyclerView recycleView;
    private View view;
    private FirebaseRecyclerAdapter<Item, ViewHolder> mAdapter;
    private FirebaseRecyclerOptions<Item> options;
    private Query query;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        recycleView = view.findViewById(R.id.storelist);
        query = FirebaseDatabase.getInstance().getReference().child("all_items");
        options = new FirebaseRecyclerOptions.Builder<Item>().setQuery(query, Item.class).build();

        mAdapter = new FirebaseRecyclerAdapter<Item, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Item model) {

                holder.initializeView(model.getName(),model.getPrice(), model.getImage());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);

                return new ViewHolder(view);
            }
        };

        recycleView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(false);
        recycleView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

}