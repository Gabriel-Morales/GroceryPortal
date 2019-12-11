package com.kwikkart.kwikkart;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kwikkart.kwikkart.model.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";

    private EditText signupInputName, signupInputEmail, signupInputPassword, signupInputStreet, signupInputCity, signupInputZipCode, signupInputPhoneNumber, signupInputPreferences;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
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


    /**
     * createNewUser
     * This method takes the registration form input to create a new user account
     * upon success, user is signed in automatically
     *
     * @param view View
     */
    public void createNewUser(View view){
        String email = signupInputEmail.getText().toString();
        String password = signupInputPassword.getText().toString();
        String name = signupInputName.getText().toString();
        String phoneNumber = signupInputPhoneNumber.getText().toString();
        String city = signupInputCity.getText().toString();
        String street = signupInputStreet.getText().toString();
        String zipcode = signupInputZipCode.getText().toString();
        String preference = signupInputPreferences.getText().toString();
        User user = new User(email, name, street + " " + city + " " + zipcode, phoneNumber, preference);

        //This adds user the the authentication database
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "createUserWithEmail:success");
                }
            }
        });


        //This adds the metadata for new user into the user firestore collection
        fDatabase.collection("users").document(email).set(user.toMap());
        startActivity(new Intent(RegistrationActivity.this, Home.class));
    }

}
