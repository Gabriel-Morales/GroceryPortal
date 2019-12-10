package com.kwikkart.kwikkart;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.kwikkart.kwikkart.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    private Button logoutButton, saveButton;
    private EditText editName, editEmail, editPassword, editPhone, editAddress, editCity, editZip, editDelivery;
    private TextView name, email, password, phone, address, city, zipcode, delivery;

    private User user;
    private String emailAddress;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mAuth = FirebaseAuth.getInstance();
        emailAddress = mAuth.getCurrentUser().getEmail();
        fDatabase = FirebaseFirestore.getInstance();

        View view = inflater.inflate(R.layout.fragment_account, container, false);
        logoutButton = view.findViewById(R.id.logoutButton);
        saveButton = view.findViewById(R.id.saveButton);
        editPhone = view.findViewById(R.id.editPhone);
        editName = view.findViewById(R.id.editName);
        editEmail = view.findViewById(R.id.editEmail);
        editPassword = view.findViewById(R.id.editPassword);
        editAddress = view.findViewById(R.id.editAddress);
        editCity = view.findViewById(R.id.editCity);
        editZip = view.findViewById(R.id.editZip);
        editDelivery = view.findViewById(R.id.editDelivery);

        DocumentReference docRef = fDatabase.collection("users").document(emailAddress);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);
                editName.setHint(user.getName());
                editEmail.setHint(user.getEmail());
                //TODO: figure out how to access password
                editPhone.setHint(user.getPhoneNumber());
                editAddress.setHint(user.getBillingAddress());
                editDelivery.setHint(user.getPreference());

                editName.setVisibility(View.VISIBLE);
                editEmail.setVisibility(View.VISIBLE);
                editPhone.setVisibility(View.VISIBLE);
                editAddress.setVisibility(View.VISIBLE);
                editDelivery.setVisibility(View.VISIBLE);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getFragmentManager().popBackStack();
                logoutButton = null;
                startActivity(intent);
            }
        });

        return view;
    }

}
