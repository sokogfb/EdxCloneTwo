package com.blogspot.techtibet.edxclone;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button mGsignInBtn;
    private Button mLogin;
    private EditText mUsername;
    private EditText mPassword;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;
    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar=(Toolbar)findViewById(R.id.logintoolbar);
        mUsername=(EditText)findViewById(R.id.login_uname);
        mPassword=(EditText)findViewById(R.id.login_pass);
        mLogin=(Button)findViewById(R.id.login_btn);
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();
        mDialog=new ProgressDialog(this);
        mDialog.setTitle("Loading");
        mDialog.setMessage("Please wait while logging in");
        mDialog.setCanceledOnTouchOutside(false);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mGsignInBtn=(Button)findViewById(R.id.signin_btn);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mUsername.getText().toString();
                String password=mPassword.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    mDialog.show();
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                                mDialog.dismiss();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "Please fill both username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mGsignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence text[]=new CharSequence[]{"john@gmail.com","rob@gmail.com"};
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Choose an account");
                builder.setItems(text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(text[i].equals("john@gmail.com")){
                            Toast.makeText(LoginActivity.this, "Thanks for selecting:But this feature is yet to implement",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }
}
