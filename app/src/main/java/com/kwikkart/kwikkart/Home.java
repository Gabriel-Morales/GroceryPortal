package com.kwikkart.kwikkart;


import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class Home extends AppCompatActivity  implements BottomNavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setStatusBarColor(Color.WHITE);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setOnNavigationItemSelectedListener(this);


    }


    /* Note: Some of the below code has been adapted from Belal Khan - "Bottom navigation android example using fragments" */

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment destFragment;

        switch (menuItem.getItemId())
        {
            case R.id.cart:
                destFragment = new CartFragment();
                createFragment(destFragment);
                return true;
            case R.id.track:
                destFragment = new TrackFragment();
                createFragment(destFragment);
                return true;
            case R.id.account:
                destFragment = new AccountFragment();
                createFragment(destFragment);
                return true;
            case R.id.settings:
                destFragment = new SettingsFragment();
                createFragment(destFragment);
                return true;
            case R.id.home:
                destFragment = new HomeFragment();
                createFragment(destFragment);
                return true;
        }

        return false;
    }


    private void createFragment(Fragment fragment)
    {

        if (fragment == null)
        {
            return;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fragment).commit();

    }

}
