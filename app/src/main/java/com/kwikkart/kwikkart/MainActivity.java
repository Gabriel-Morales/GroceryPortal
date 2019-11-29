package com.kwikkart.kwikkart;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button lButton;
    private View loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getWindow().setStatusBarColor(Color.rgb(100 , 193, 255));

       lButton = findViewById(R.id.loginButton);
       loginView = findViewById(R.id.loginView);
       loginView.setBackgroundColor(Color.WHITE);

    }


    public void loginButtonClicked(View view) {

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }


}
