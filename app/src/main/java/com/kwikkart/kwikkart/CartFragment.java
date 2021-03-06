package com.kwikkart.kwikkart;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CartFragment extends Fragment {


    private Toolbar cartToolbar;
    private FloatingActionButton floatingActionButton;
    private View view;
    private RecyclerView recyclerView;
    public static CartAdapter notifCartAdapter;

    /**
     * onCreateView
     * @param inflater @NonNull LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartToolbar = view.findViewById(R.id.cartToolbar);
        floatingActionButton = view.findViewById(R.id.fabCheck);
        recyclerView = view.findViewById(R.id.cartRecycler);


        final CartAdapter cartAdapter = new CartAdapter(getContext(), cartToolbar);
        notifCartAdapter = cartAdapter;
        recyclerView.setAdapter(cartAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(false);
        recyclerView.setLayoutManager(layoutManager);


        if (HomeFragment.getCart().size() == 0)
        {
            cartToolbar.getMenu().getItem(0).setEnabled(false);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HomeFragment.getCart().isEmpty())
                {
                    return;
                }
                else {
                    Intent intent = new Intent(getActivity(), Checkout.class);

                    ActivityOptionsCompat aco = ActivityOptionsCompat.makeClipRevealAnimation(getView(), (int) floatingActionButton.getX(), (int) floatingActionButton.getY(), 200, 200);

                    startActivity(intent, aco.toBundle());
                }
            }
        });


        cartToolbar.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Clear shopping cart")
                        .setMessage("Are you sure you would like to clear your shopping cart?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                int range = HomeFragment.getCart().size();
                                HomeFragment.clearCart();
                                cartAdapter.notifyItemRangeRemoved(0, range);
                                cartToolbar.setTitle("0 items in cart");
                                cartToolbar.getMenu().getItem(0).setEnabled(false);

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return true;
            }
        });

        cartToolbar.setTitle(HomeFragment.getCart().size() + " items in cart");


         return view;
    }

    /**
     * onResume
     */
    @Override
    public void onResume() {
        super.onResume();
        cartToolbar.setTitle(HomeFragment.getCart().size() + " items in cart");
    }
}