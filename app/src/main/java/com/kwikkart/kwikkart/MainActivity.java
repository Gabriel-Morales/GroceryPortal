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
    private Button signUpButton;
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

       lButton = findViewById(R.id.loginButton);
       signUpButton = findViewById(R.id.button);
       loginView = findViewById(R.id.loginView);
       loginView.setBackgroundColor(Color.WHITE);

    }

    public void loginButtonClicked(View view) {

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void registerButtonClicked(View view){

        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

}
