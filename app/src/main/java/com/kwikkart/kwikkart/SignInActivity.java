package com.kwikkart.kwikkart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignInActivity extends AppCompatActivity {

    private EditText uName;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        getWindow().setStatusBarColor(Color.rgb(100 , 193, 255));

        uName = findViewById(R.id.uNameText);
        view = findViewById(R.id.signInView);
        view.setBackgroundColor(Color.WHITE);

    }
}
