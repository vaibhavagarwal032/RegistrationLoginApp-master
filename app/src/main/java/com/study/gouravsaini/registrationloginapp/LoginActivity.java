package com.study.gouravsaini.registrationloginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.study.gouravsaini.registrationloginapp.Common.Common;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.study.gouravsaini.registrationloginapp.Model.User;

public class LoginActivity extends AppCompatActivity {
    EditText admissionno,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        admissionno=findViewById(R.id.admissionno);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog= new ProgressDialog(LoginActivity.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(admissionno.getText().toString()).exists()){
                            mDialog.dismiss();

                            User user=dataSnapshot.child(admissionno.getText().toString()).getValue(User.class);
                            user.setAdmissionno(admissionno.getText().toString());

                            if (user.getPassword().equals(password.getText().toString())){
                                Intent myintent=new Intent(LoginActivity.this,HomeActivity.class);
                                Common.currentUser=user;
                                startActivity(myintent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "User not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

}
