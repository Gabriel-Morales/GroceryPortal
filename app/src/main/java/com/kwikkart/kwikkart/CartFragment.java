package com.kwikkart.kwikkart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.kwikkart.kwikkart.R;

public class CartFragment extends Fragment {

    private Toolbar cartToolbar;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cart, container, false);
        cartToolbar = view.findViewById(R.id.cartToolbar);

        cartToolbar.setTitle(HomeFragment.getCart().size() + " items in cart");

         return view;
    }


}