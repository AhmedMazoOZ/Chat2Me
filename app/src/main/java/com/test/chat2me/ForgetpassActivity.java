package com.test.chat2me;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetpassActivity extends AppCompatActivity {
    EditText send_email;
    Button btn_send;
    FirebaseAuth firebaseAuth;
    ProgressBar pgsBar;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        send_email=findViewById(R.id.send_email);
        btn_send=findViewById(R.id.btn_reset);
        firebaseAuth=FirebaseAuth.getInstance();
        pgsBar.setVisibility(v.GONE);
        btn_send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pgsBar.setVisibility(v.VISIBLE);
                String email=send_email.getText().toString();
                if(email.equals("")){
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
                }else {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Pleas check your email",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ForgetpassActivity.this,LoginActivity.class));
                            }else {
                                String error=task.getException().getMessage();
                                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
