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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    View v;


    EditText email,pass;
    Button Login;
    TextView ForgetPass;
    TextView Signup;
    ProgressBar pgsBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chat 2 Me");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email=(EditText)findViewById(R.id.edemail);
        pass=(EditText)findViewById(R.id.edPassword);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        Login=(Button)findViewById(R.id.loginbu);
        ForgetPass=(TextView)findViewById(R.id.ForgetPass);
        Signup=(TextView)findViewById(R.id.Sign_UP);
        pgsBar.setVisibility(v.GONE);
        auth = FirebaseAuth.getInstance();
    }

    public void Login(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        String Email = email.getText().toString();
        String Password = pass.getText().toString();

        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password)) {
            Toast.makeText(LoginActivity.this, "All Field are required", Toast.LENGTH_LONG).show();
        } else {
            auth.signInWithEmailAndPassword(Email, Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent=new Intent(LoginActivity.this,HomePage.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();

                            }else {
                                Toast.makeText(LoginActivity.this, "Authentacation faild", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        }
        //startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void CreateAcc(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    public void ForgetPass(View v) {
        pgsBar.setVisibility(v.VISIBLE);
        startActivity(new Intent(LoginActivity.this, ForgetpassActivity.class));
    }
}
