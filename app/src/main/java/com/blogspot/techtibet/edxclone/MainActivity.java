package com.blogspot.techtibet.edxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button mRegBtn;
    private Button mLoginBtn;
    private FirebaseAuth mAuth;
    private EditText mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearch=findViewById(R.id.main_search);
        mSearch.setSelected(false);
      mRegBtn=findViewById(R.id.create_btn);
      mLoginBtn=findViewById(R.id.loginbtn);
      mAuth=FirebaseAuth.getInstance();

      mRegBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
              startActivity(intent);
          }
      });
      mLoginBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(MainActivity.this,LoginActivity.class);
              startActivity(intent);
          }
      });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();
        if(user!=null){
            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
