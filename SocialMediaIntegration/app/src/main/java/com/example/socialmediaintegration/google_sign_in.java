package com.example.socialmediaintegration;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class google_sign_in extends AppCompatActivity {

    private FirebaseAuth mfirebaseauth;
    private ImageView profilepic;
    private TextView names;
    private TextView emailid;

    private Button log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sign_in);
        names=findViewById(R.id.name);
        emailid=findViewById(R.id.email);

        profilepic=findViewById(R.id.profile);
        mfirebaseauth=FirebaseAuth.getInstance();
        log_out=findViewById(R.id.button2);
        FirebaseUser user=mfirebaseauth.getCurrentUser();
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(google_sign_in.this,MainActivity.class));
                mfirebaseauth.signOut();

            }
        });
        if(user!=null){
            names.setText(user.getDisplayName());
            emailid.setText(user.getEmail());
            Glide.with(this).load(user.getPhotoUrl()).into(profilepic);

        }

    }
}