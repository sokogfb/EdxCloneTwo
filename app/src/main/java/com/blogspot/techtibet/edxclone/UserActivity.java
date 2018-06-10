package com.blogspot.techtibet.edxclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView mUsername;
    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mToolbar=(Toolbar)findViewById(R.id.usertoolbar);
        mUsername=(TextView)findViewById(R.id.user_username);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //firebase
        mAuth=FirebaseAuth.getInstance();
        mStore=FirebaseFirestore.getInstance();

        mStore.collection("Users").document(mAuth.getCurrentUser().getUid()).addSnapshotListener(this,new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {
           if(documentSnapshot.exists()){
               String username=documentSnapshot.getString("publicname");
               mUsername.setText(username);
           }
            }
        });
    }
}
