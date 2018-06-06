package com.blogspot.techtibet.edxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mRegBtn;
    private Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      mRegBtn=(Button)findViewById(R.id.create_btn);
      mLoginBtn=(Button)findViewById(R.id.loginbtn);
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
}
