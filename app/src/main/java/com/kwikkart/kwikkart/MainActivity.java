package com.kwikkart.kwikkart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button lButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       getWindow().setStatusBarColor(Color.rgb(100 , 193, 255));
        lButton = findViewById(R.id.loginButton);


    }


    public void loginButtonClicked(View view) {



    }


}
