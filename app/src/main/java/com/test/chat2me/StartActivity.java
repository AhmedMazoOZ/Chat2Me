package com.test.chat2me;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class StartActivity extends AppCompatActivity {
    View v;
    @BindView(R.id.loginbu)
    Button Login;
    @BindView(R.id.Registerbu)
    TextView Signup;
    @BindView(R.id.pBar)
    ProgressBar pgsBar;

    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser!=null){
            Intent intent=new Intent(StartActivity.this,HomePage.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        pgsBar.setVisibility(v.GONE);




    }

    @OnClick(R.id.loginbu)
    public void Login(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        startActivity(new Intent(StartActivity.this, LoginActivity.class));
    }

    @OnClick(R.id.loginbu)
    public void Createacc(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        startActivity(new Intent(StartActivity.this, RegisterActivity.class));
    }
}
