package com.kwikkart.kwikkart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
import com.kwikkart.kwikkart.model.Order;
import com.kwikkart.kwikkart.model.User;

import java.nio.channels.Channel;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
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
    private Spinner couponSpinner;
    private TextView preferences;
    private Toolbar toolbar;
    private TextView couponText;
    private User user;
    private Spinner paySelect;
    private FloatingActionButton doneButton;
    private double total;

    private final String CHANNEL_ID = "Unique_Channel";

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
        couponText = findViewById(R.id.couponText);
        couponSpinner = findViewById(R.id.couponSpinner);

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
        couponText.setVisibility(View.INVISIBLE);
        couponSpinner.setVisibility(View.INVISIBLE);


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createNotificationChannel();
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
                totalText.setVisibility(View.VISIBLE);
                couponText.setVisibility(View.VISIBLE);
                couponSpinner.setVisibility(View.VISIBLE);
            }
        });



        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new Runnable() {
            @Override
            public void run() {
                calculateTotal();
                grandTotal.setText(String.format("$%.2f", total));

            }
        });


        String[] cards= {"Visa", "AMEX", "Discover"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, cards);
        paySelect.setAdapter(adapter);

        String[] availCoupons = {"None", "10% off", "15% off",  "20%off"};
        ArrayAdapter<String> couponAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, availCoupons);
       couponSpinner.setAdapter(couponAdapter);

       couponSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               double newPrice = total;
               switch (position)
               {
                   case 0:
                       newPrice = total;
                       break;
                   case 1:
                       newPrice = total - (.10 * total);
                       break;
                   case 2:
                       newPrice = total - (.15 * total);
                       break;
                   case 3:
                       newPrice = total - (.20 * total);
                       break;
                   default:
                       break;
               }

               grandTotal.setText(String.format("$%.2f", newPrice));

           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //TODO: Upload  just number of  items in cart and current date to database. Pick a random num from 1 to 7 and that's the delivery date.
                Random r = new Random();
                int timeFrame = r.nextInt(8) + 1;
                int itemsInCart = HomeFragment.getCart().size();

                Calendar today = Calendar.getInstance();
                String currentMonth = today.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
                String currentDayOfMonth = today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
                String currentDayOfMonthNum = ""+today.get(Calendar.DAY_OF_MONTH);
                String orderDate =  currentDayOfMonth + ", " + currentMonth + " " + currentDayOfMonthNum;

                today.add(Calendar.DAY_OF_MONTH, timeFrame);
                String deliveryDate = today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US) + ", " + today.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + today.get(Calendar.DAY_OF_MONTH);


                String orderID = orderIDgen();
                Order order = new Order(orderDate, deliveryDate, ""+itemsInCart, ""+total);
                fDatabase.collection("users").document(email).collection("order").document(orderID).set(order.toMap());

                Notification builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID).setSmallIcon(R.drawable.ic_notifications_black_24dp).setContentTitle("Kwik Kart Delivery").setContentText("Order placed on " + orderDate).setPriority(NotificationCompat.PRIORITY_HIGH).setDefaults(NotificationCompat.DEFAULT_SOUND).build();
                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(192, builder);


                int size = HomeFragment.getCart().size();
                HomeFragment.clearCart();
                CartFragment.notifCartAdapter.notifyItemRangeRemoved(0, size);
                finish();

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

        total = 0;

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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private String orderIDgen()
    {
        String str = "";
        Random r = new Random();

        int i = 0;
        while (i < 10)
        {
            str += r.nextInt(8321);
            i += 1;
        }

        return str;
    }

}
