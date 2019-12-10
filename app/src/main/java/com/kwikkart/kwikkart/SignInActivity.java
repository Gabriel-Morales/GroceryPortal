package com.kwikkart.kwikkart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText uName;
    private EditText passText;
    private View view;
    private FirebaseAuth mAuth;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        getWindow().setStatusBarColor(Color.WHITE);

        uName = findViewById(R.id.uNameText);
        view = findViewById(R.id.signInView);
        passText = findViewById(R.id.passText);

        view.setBackgroundColor(Color.parseColor("#FAFAFA"));
        mAuth = FirebaseAuth.getInstance();
        intent = new Intent(this, Home.class);
        if (mAuth.getCurrentUser() != null) {
            startActivity(intent);
        }



    }

    public void validateUser(View view)
    {

        String password = passText.getText().toString();
        String username = uName.getText().toString();

        if (password.equals("")|| username.equals(""))
        {
            Toast.makeText(SignInActivity.this, "Fields cannot be empty.",
                    Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {

                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SignInActivity.this, "Invalid email or password.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    //No need to implement this to the tiniest detail. It just notifies that an email has been sent.
    public void forgetPassword(View view)
    {
        Toast.makeText(SignInActivity.this, "Reset password link sent to email.",
                Toast.LENGTH_SHORT).show();
    }

}
