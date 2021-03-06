package com.example.socialmediaintegration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class fblogin extends AppCompatActivity {
    private FirebaseAuth mfirebaseauth;
    private ImageView profilepic;
    private TextView names;
    private TextView emailid;

    private LoginButton log_out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Facebook account");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fblogin);
        names=findViewById(R.id.name);
        emailid=findViewById(R.id.email);
        profilepic=findViewById(R.id.profile);
        mfirebaseauth=FirebaseAuth.getInstance();
        log_out=findViewById(R.id.log_out);
        FirebaseUser user=mfirebaseauth.getCurrentUser();
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(fblogin.this,MainActivity.class));
                mfirebaseauth.signOut();
                LoginManager.getInstance().logOut();

            }
        });
        if(user!=null){
            names.setText(user.getDisplayName());
            emailid.setText(user.getEmail());
            Glide.with(this).load(user.getPhotoUrl()).into(profilepic);
        }

    }

}