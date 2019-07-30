package com.test.chat2me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    View v;


    EditText Name ,edemail,edPassword,edConPassword;
    RadioButton Male ,Femal;
    Button loginbu;
    TextView CreateAccount;
    ProgressBar pgsBar;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    String UserName, Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat 2 Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Name=(EditText)findViewById(R.id.Name);
        edemail=(EditText)findViewById(R.id.edemail);
        edPassword=(EditText)findViewById(R.id.edPassword);

        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        pgsBar.setVisibility(v.GONE);

        auth = FirebaseAuth.getInstance();

    }

    @OnClick(R.id.CreateAccount)
    public void CreateAcc(View v) {
        pgsBar.setVisibility(v.VISIBLE);

        UserName = Name.getText().toString();
        Email = edemail.getText().toString();
        Password = edPassword.getText().toString();
        if (TextUtils.isEmpty(UserName) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
            Toast.makeText(RegisterActivity.this, "All Field are required", Toast.LENGTH_LONG).show();
        } else if (Password.length() < 8) {
            Toast.makeText(RegisterActivity.this, "Password Must be at least 8 character", Toast.LENGTH_LONG).show();
        } else {
            GetData(UserName, Email, Password);
        }


    }

    @OnClick(R.id.CreateAccount)
    public void Login(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    public void GetData(final String UserName, final String Email, String Password) {
        auth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String UserId = firebaseUser.getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(UserId);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", UserId);
                            hashMap.put("username", UserName);
                            hashMap.put("imageUrl", "default");
                            hashMap.put("status", "Offline");
                            hashMap.put("search", UserName.toLowerCase());


                            databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Can't register with this email and password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
