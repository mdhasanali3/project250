package com.example.project_250;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;


import com.example.project_250.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private DrawerLayout mdr;
    private ActionBarDrawerToggle mtg;
    private AppBarConfiguration mAppBarConfiguration;
    int count=0;
    ViewFlipper viewFlipper;
    Button next,button,skip;
    TextView text1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdr=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mtg=new ActionBarDrawerToggle(this,mdr,R.string.open,R.string.close);

        viewFlipper=(ViewFlipper)findViewById(R.id.view_fliper);
        next=(Button)findViewById(R.id.btn2);
        next.setOnClickListener(this);
        skip=(Button) findViewById(R.id.btn3);
        text1=(TextView) findViewById(R.id.tx1);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ar_class();
            }
        });

        mdr.addDrawerListener(mtg);
        mtg.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button=(Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ar_class();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(mtg.onOptionsItemSelected(item)) return true;
        return super.onOptionsItemSelected(item);
    }

    public void ar_class(){
        Intent intent=new Intent(this, ar_class.class);
        startActivity(intent);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.nav_home){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.nav_about){
            Intent intent=new Intent(this, about.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.nav_exit){
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v==next){
            count++;
            viewFlipper.showNext();
        }
        if(count==5){
            button.setVisibility(View.VISIBLE);
            next.setVisibility(View.INVISIBLE);
            skip.setVisibility(View.INVISIBLE);
        }
    }
}
