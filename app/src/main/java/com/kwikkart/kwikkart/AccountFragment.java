package com.kwikkart.kwikkart;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;

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

    private User user;
    private String emailAddress;

    private FirebaseAuth mAuth;
    private FirebaseFirestore fDatabase;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Logs the user out if the logoutButton is clicked
     * Updates the user information when the user enters new information and clicks save
     * Makes sure the user sees what they type
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
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

        final DocumentReference docRef = fDatabase.collection("users").document(emailAddress);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {

            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                user = documentSnapshot.toObject(User.class);

                editName.setHint(user.getName());
                editEmail.setHint(user.getEmail());
                editPassword.setHint("**********");
                editPhone.setHint(user.getPhoneNumber());

                String billingAddress = user.getBillingAddress();
                String address[] = billingAddress.split(" ");
                editAddress.setHint(address[0] + " " + address[1] + " " + address[2]);
                if(address.length == 6)
                {
                    editCity.setHint(address[3] + " " + address[4]);
                    editZip.setHint(address[5]);
                }
                else if(address.length == 7)
                {
                    editCity.setHint(address[3] + " " + address[4] + " " + address[5]);
                    editZip.setHint(address[6]);
                }

                editDelivery.setHint(user.getPreference());
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editName.getText().equals("") && editName.getText() != null && !TextUtils.isEmpty(editName.getText()))
                {
                    user.setName(editName.getText().toString());
                }

                if(!editEmail.getText().equals("") && editEmail.getText() != null && !TextUtils.isEmpty(editEmail.getText()))
                {
                    user.setEmail(editEmail.getText().toString());
                }

                if(!editPhone.getText().equals("") && editPhone.getText() != null && !TextUtils.isEmpty(editPhone.getText()))
                {
                    user.setPhoneNumber(editPhone.getText().toString());
                }

                if(!editDelivery.getText().equals("") && editDelivery.getText() != null && !TextUtils.isEmpty(editDelivery.getText()))
                {
                    user.setPreference(editDelivery.getText().toString());
                }

                if(!editAddress.getText().equals("") && editAddress.getText() != null && !TextUtils.isEmpty(editAddress.getText()))
                {
                    String newAddress = user.getBillingAddress();
                    String splitAddress[] = newAddress.split(" ");

                    newAddress = editAddress.getText().toString();

                    if(!editCity.getText().equals("") && editCity.getText() != null && !TextUtils.isEmpty(editCity.getText()))
                    {
                        newAddress = newAddress + " " + editCity.getText().toString();

                        if(!editZip.getText().equals("") && editZip.getText() != null && !TextUtils.isEmpty(editZip.getText()))
                        {
                            newAddress = newAddress + " " + editZip.getText().toString();
                        }
                        else
                        {
                            if(splitAddress.length == 6)
                            {
                                newAddress = newAddress + " " + splitAddress[5];
                            }
                            else if(splitAddress.length == 7)
                            {
                                newAddress = newAddress + " " + splitAddress[6];
                            }
                        }
                    }
                    else
                    {
                        if(splitAddress.length == 6)
                        {
                            newAddress = newAddress + " " + splitAddress[3] + " " + splitAddress[4];
                        }
                        else if(splitAddress.length == 7)
                        {
                            newAddress = newAddress + " " + splitAddress[3] + " " + splitAddress[4] + " " + splitAddress[5];
                        }

                        if(!editZip.getText().equals("") && editZip.getText() != null && !TextUtils.isEmpty(editZip.getText()))
                        {
                            newAddress = newAddress + " " + editZip.getText().toString();
                        }
                        else
                        {
                            if(splitAddress.length == 6)
                            {
                                newAddress = newAddress + " " + splitAddress[5];
                            }
                            else if(splitAddress.length == 7)
                            {
                                newAddress = newAddress + " " + splitAddress[6];
                            }
                        }
                    }
                    user.setBillingAddress(newAddress);
                }
                else
                {
                    String newAddress = user.getBillingAddress();
                    String splitAddress[] = newAddress.split(" ");

                    newAddress = splitAddress[0] + " " + splitAddress[1] + " " + splitAddress[2];

                    if(!editCity.getText().equals("") && editCity.getText() != null && !TextUtils.isEmpty(editCity.getText()))
                    {
                        newAddress = newAddress + " " + editCity.getText().toString();

                        if(!editZip.getText().equals("") && editZip.getText() != null && !TextUtils.isEmpty(editZip.getText()))
                        {
                            newAddress = newAddress + " " + editZip.getText().toString();
                        }
                        else
                        {
                            if(splitAddress.length == 6)
                            {
                                newAddress = newAddress + " " + splitAddress[5];
                            }
                            else if(splitAddress.length == 7)
                            {
                                newAddress = newAddress + " " + splitAddress[6];
                            }
                        }
                    }
                    else
                    {
                        if(splitAddress.length == 6)
                        {
                            newAddress = newAddress + " " + splitAddress[3] + " " + splitAddress[4];
                        }
                        else if(splitAddress.length == 7)
                        {
                            newAddress = newAddress + " " + splitAddress[3] + " " + splitAddress[4] + " " + splitAddress[5];
                        }

                        if(!editZip.getText().equals("") && editZip.getText() != null && !TextUtils.isEmpty(editZip.getText()))
                        {
                            newAddress = newAddress + " " + editZip.getText().toString();
                        }
                        else
                        {
                            if(splitAddress.length == 6)
                            {
                                newAddress = newAddress + " " + splitAddress[5];
                            }
                            else if(splitAddress.length == 7)
                            {
                                newAddress = newAddress + " " + splitAddress[6];
                            }
                        }
                    }
                    user.setBillingAddress(newAddress);
                }
                docRef.update(user.toMap());
            }
        });

        return view;
    }

}
