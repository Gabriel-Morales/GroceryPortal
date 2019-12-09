package com.kwikkart.kwikkart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kwikkart.kwikkart.model.User;

public class Checkout extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;
    private TextView nameText;
    private ProgressBar progressBar;
    private TextView addressText;
    private String email;
    private Toolbar toolbar;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        getWindow().setStatusBarColor(Color.parseColor("#1B67D8"));

        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();
        fDatabase = FirebaseFirestore.getInstance();
        nameText = findViewById(R.id.nameText);
        addressText = findViewById(R.id.addressText);
        progressBar = findViewById(R.id.checkProgress);
        nameText.setVisibility(View.INVISIBLE);
        addressText.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.myToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       getSupportActionBar().setTitle("");
       getWindow().getDecorView().setSystemUiVisibility(0);

        DocumentReference docRef = fDatabase.collection("users").document(email);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                nameText.setText(user.getName());
                addressText.setText(user.getBillingAddress());
                nameText.setVisibility(View.VISIBLE);
                addressText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
