package com.example.secondar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.secondar.R;
import com.example.secondar.ar_class;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mdr;
    private ActionBarDrawerToggle mtg;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mdr=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mtg=new ActionBarDrawerToggle(this,mdr,R.string.open,R.string.close);

        mdr.addDrawerListener(mtg);
        mtg.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button=(Button) findViewById(R.id.btn);
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
            Intent intent=new Intent(this,about.class);
            startActivity(intent);
        }else if(item.getItemId()==R.id.nav_exit){
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        return false;
    }
}
