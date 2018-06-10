package com.blogspot.techtibet.edxclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button mGoogleBtn;
    private Button mFaceBookBtn;
    private Button mRegBtn;
    private EditText mEmail;
    private EditText mFullName;
    private EditText mPublicName;
    private EditText mPassword;
    private ProgressDialog mDialog;
    //firebase
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mToolbar=(Toolbar)findViewById(R.id.registertoolbar);
        mGoogleBtn=(Button)findViewById(R.id.facebookreg);
        mFaceBookBtn=(Button)findViewById(R.id.googlereg);
        mRegBtn=(Button)findViewById(R.id.createbtn);
        mEmail=(EditText)findViewById(R.id.emailtf);
        mFullName=(EditText)findViewById(R.id.fullnametf);
        mPublicName=(EditText)findViewById(R.id.publicnametf);
        mPassword=(EditText)findViewById(R.id.passwordtf);
        mDialog=new ProgressDialog(this);
        mDialog.setTitle("Loading");
        mDialog.setMessage("Please wait while creating new account");
        mDialog.setCanceledOnTouchOutside(false);
        //firebase
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email=mEmail.getText().toString();
                final String fullname=mFullName.getText().toString();
                final String publicname=mPublicName.getText().toString();
                String password=mPassword.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(publicname) && !TextUtils.isEmpty(password)){
                    mDialog.show();
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Map<String,Object> map=new HashMap<>();
                           map.put("fullname",fullname);
                           map.put("publicname",publicname);
                           mStore.collection("Users").document(mAuth.getCurrentUser().getUid()).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Toast.makeText(RegisterActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
                                       Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
                                       intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                       startActivity(intent);
                                       finish();
                                       mDialog.dismiss();
                                   }else{
                                       Toast.makeText(RegisterActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        mDialog.dismiss();
                                   }
                               }
                           }).addOnFailureListener(new OnFailureListener() {
                               @Override
                               public void onFailure(@NonNull Exception e) {
                                   Toast.makeText(RegisterActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    mDialog.dismiss();
                               }
                           });


                       }else{
                           Toast.makeText(RegisterActivity.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                       }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterActivity.this, "Error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this, "PLease,Don't leave empty field!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
