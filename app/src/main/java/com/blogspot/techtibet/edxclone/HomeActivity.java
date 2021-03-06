package com.blogspot.techtibet.edxclone;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SectionPagerAdapter mPagerAdapter;
    private TabLayout mTablayout;
    private LinearLayout linearLayout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar=(Toolbar)findViewById(R.id.hometoolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Courses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager=(ViewPager)findViewById(R.id.tappager);
        mTablayout=(TabLayout)findViewById(R.id.maintab);
        linearLayout=(LinearLayout)findViewById(R.id.lineartool);
        editText=(EditText)findViewById(R.id.edittext);
        Drawable drawable= ContextCompat.getDrawable(this,R.mipmap.ic_user);
        mToolbar.setNavigationIcon(drawable);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });



        mPagerAdapter=new SectionPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);
        mTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                InputMethodManager imm=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


                if(tab.getText().equals("Courses")){
                    getSupportActionBar().setTitle("Courses");
                    imm.hideSoftInputFromInputMethod(editText.getWindowToken(),InputMethodManager.RESULT_HIDDEN);
                    linearLayout.setVisibility(View.INVISIBLE);
                }
                if(tab.getText().equals("Discovery")){
                    getSupportActionBar().setTitle("Discovery");
                    linearLayout.setVisibility(View.VISIBLE);
                    editText.requestFocus();
                    View view=HomeActivity.this.getCurrentFocus();
                    if(view!=null){

                        imm.showSoftInput(editText,0);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
                    }else{

                        imm.hideSoftInputFromInputMethod(editText.getWindowToken(),0);
                    }


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.settingbtn){
            Intent intent =new Intent(HomeActivity.this,SettingActivity.class);
            startActivity(intent);
            return true;
        }
        else{
            return  false;
        }
    }
}
