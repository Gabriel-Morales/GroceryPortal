package com.kwikkart.kwikkart;



import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment  {

    private Switch turnOff, confirm, leaving, arrive;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Sets all of the notifications on by default
     * Turns all notifications off if turnOff switch is on
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        turnOff = view.findViewById(R.id.turnOff);
        confirm = view.findViewById(R.id.confirm);
        leaving = view.findViewById(R.id.leaving);
        arrive = view.findViewById(R.id.arrive);

        confirm.setChecked(true);
        leaving.setChecked(true);
        arrive.setChecked(true);

        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirm.setChecked(false);
                leaving.setChecked(false);
                arrive.setChecked(false);
            }
        });

        return view;
    }
}
