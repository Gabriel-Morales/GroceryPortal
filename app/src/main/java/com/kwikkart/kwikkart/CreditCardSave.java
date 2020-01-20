package com.kwikkart.kwikkart;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreditCardSave  extends AppCompatActivity
{
    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputStreet, signupInputCity, signupInputZipCode, signupInputPhoneNumber, signupInputPreferences;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getWindow().setStatusBarColor(Color.WHITE);

        signupInputEmail = findViewById(R.id.email);
        signupInputPassword = findViewById(R.id.password);
        signupInputName = findViewById(R.id.name);
        signupInputPhoneNumber = findViewById(R.id.phoneNumber);
        signupInputCity = findViewById(R.id.city);
        signupInputStreet = findViewById(R.id.number);
        signupInputZipCode = findViewById(R.id.zipcode);
        signupInputPreferences = findViewById(R.id.preference);

        mAuth = FirebaseAuth.getInstance();
        fDatabase = FirebaseFirestore.getInstance();


        btnSignUp = findViewById(R.id.registerButton);
    }
}

