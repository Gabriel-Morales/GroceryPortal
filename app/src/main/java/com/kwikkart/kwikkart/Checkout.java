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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kwikkart.kwikkart.model.Item;
import com.kwikkart.kwikkart.model.User;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Checkout extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;
    private TextView nameText; //Y
    private ProgressBar progressBar;

    private TextView totalText;
    private TextView payText;
    private EditText cardNum;
    private EditText cvv;
    private EditText expire;
    private EditText holderName;
    private TextView personalInfo;
    private TextView emailText;
    private TextView numText;
    private TextView delivPref;

    private TextView addressText;
    private TextView grandTotal;
    private TextView emailInfo;
    private TextView phone;
    private String email;
    private TextView preferences;
    private Toolbar toolbar;
    private User user;
    private Spinner paySelect;
    private FloatingActionButton doneButton;
    private double total;


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
        grandTotal = findViewById(R.id.grandTotal);
        paySelect = findViewById(R.id.paymentSelect);
        emailInfo = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        preferences = findViewById(R.id.preferences);
        totalText = findViewById(R.id.totalText);

        payText = findViewById(R.id.payText);
        cardNum = findViewById(R.id.cardNum);
        cvv = findViewById(R.id.cvv);
        expire = findViewById(R.id.expire);
        holderName = findViewById(R.id.holderName);
        personalInfo = findViewById(R.id.personalInfo);
        emailText = findViewById(R.id.emailText);
        numText = findViewById(R.id.numText);
        delivPref = findViewById(R.id.deliPref);
        payText = findViewById(R.id.payText);
        cardNum = findViewById(R.id.cardNum);
        doneButton = findViewById(R.id.doneButton);


        paySelect.setVisibility(View.INVISIBLE);
        grandTotal.setVisibility(View.INVISIBLE);
        emailInfo.setVisibility(View.INVISIBLE);
        phone.setVisibility(View.INVISIBLE);
        preferences.setVisibility(View.INVISIBLE);
        payText = findViewById(R.id.payText);
        cardNum = findViewById(R.id.cardNum);
        cvv.setVisibility(View.INVISIBLE);
        expire.setVisibility(View.INVISIBLE);
        holderName.setVisibility(View.INVISIBLE);
        personalInfo.setVisibility(View.INVISIBLE);
        emailText .setVisibility(View.INVISIBLE);
        numText.setVisibility(View.INVISIBLE);
        delivPref.setVisibility(View.INVISIBLE);
        payText.setVisibility(View.INVISIBLE);
        cardNum.setVisibility(View.INVISIBLE);
        totalText.setVisibility(View.INVISIBLE);

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
                emailInfo.setText(user.getEmail());
                phone.setText(user.getPhoneNumber());
                preferences.setText(user.getPreference());
                nameText.setVisibility(View.VISIBLE);
                addressText.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                paySelect.setVisibility(View.VISIBLE);
                paySelect.setVisibility(View.VISIBLE);
                grandTotal.setVisibility(View.VISIBLE);
                emailInfo.setVisibility(View.VISIBLE);
                phone.setVisibility(View.VISIBLE);
                preferences.setVisibility(View.VISIBLE);
                cvv.setVisibility(View.VISIBLE);
                expire.setVisibility(View.VISIBLE);
                holderName.setVisibility(View.VISIBLE);
                personalInfo.setVisibility(View.VISIBLE);
                emailText .setVisibility(View.VISIBLE);
                numText.setVisibility(View.VISIBLE);
                delivPref.setVisibility(View.VISIBLE);
                payText.setVisibility(View.VISIBLE);
                cardNum.setVisibility(View.VISIBLE);
                totalText.setVisibility(View.INVISIBLE);
            }
        });



        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
                total = calculateTotal();
                grandTotal.setText(String.format("$%.2f", total));

            }
        });


        String[] cards= {"Visa", "AMEX", "Discover"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cards);
        paySelect.setAdapter(adapter);


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = HomeFragment.getCart().size();
                HomeFragment.clearCart();
                CartFragment.notifCartAdapter.notifyItemRangeRemoved(0, size);
                finish();

                //TODO: Upload  just number of  items in cart and current date to database. Pick a random num from 1 to 7 and that's the delivery date.

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

    private double calculateTotal()
    {

        double total = 0;

        Pattern expression = Pattern.compile("\\$([0-9]+\\.[0-9]+).*");
        for (Item item : HomeFragment.getCart())
        {
            Matcher m = expression.matcher(item.getPrice());
            while (m.find()) {
                total += Double.parseDouble(m.group(1));
            }

        }
        return total;
    }

}
