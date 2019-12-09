package com.kwikkart.kwikkart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kwikkart.kwikkart.model.Order;

public class TrackFragment extends Fragment {


    private RecyclerView trackRecycler;
    private FirestoreRecyclerAdapter<Order, TrackViewHolder> mAdapter;
    private Query query;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;
    private ProgressBar trackProgress;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_track, container, false);
        trackRecycler = view.findViewById(R.id.trackRecycler);
        toolbar = view.findViewById(R.id.trackToolbar);
        trackProgress = view.findViewById(R.id.trackProgress);
        mAuth = FirebaseAuth.getInstance();
        String email = mAuth.getCurrentUser().getEmail();

        toolbar.setTitle("Current Orders");

        query = FirebaseFirestore.getInstance().collection("users").document(email).collection("order");
        FirestoreRecyclerOptions<Order> options = new FirestoreRecyclerOptions.Builder<Order>().setQuery(query, Order.class).build();
        mAdapter = new FirestoreRecyclerAdapter<Order, TrackViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TrackViewHolder holder, int position, @NonNull Order model) {

                holder.initializeView(model.getPricePaid(), model.getDeliverDate(), model.getOrderDate(), model.getItemsOrdered());
                trackProgress.setVisibility(View.GONE);
            }

            @NonNull
            @Override
            public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_list_item, parent, false);

                return new TrackViewHolder(view);
            }
        };

        trackRecycler.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(false);
        trackRecycler.setLayoutManager(layoutManager);

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
}