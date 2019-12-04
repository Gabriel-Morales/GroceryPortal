package com.kwikkart.kwikkart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button lButton;
    private View loginView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = new Intent(this, Home.class);
        if (mAuth.getCurrentUser() != null) {
            startActivity(intent);
        }

       getWindow().setStatusBarColor(Color.WHITE);

       lButton = findViewById(R.id.loginButton);
       loginView = findViewById(R.id.loginView);
       loginView.setBackgroundColor(Color.WHITE);

    }


    public void loginButtonClicked(View view) {

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }


}
