package com.blogspot.techtibet.edxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {
    private ListView mList;
    private ArrayAdapter adapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mList=(ListView)findViewById(R.id.listview);
        mToolbar=(Toolbar)findViewById(R.id.settingtoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String[] list=new String[]{"Profile","Settings","Submit Feedback","Logout"};
        ArrayList<String> arrayList=new ArrayList<String>();
        for(int i=0; i<list.length;i++){
            arrayList.add(list[i]);
        }
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==3){
                    Intent intent=new Intent(SettingActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

    }
}
