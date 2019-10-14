package com.example.screenshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }else
        {

        }

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View rootView=getWindow().getDecorView().findViewById(android.R.id.content);
                Bitmap bitmap=getScreenShot(rootView);
                store(bitmap,"ScreenShot.png");
            }
        });
    }

    //@Override
    public void onRequestPermissionResult(int requestCode, @NonNull String[] permission,@NonNull int[] grantResult){
        if(requestCode==1000){
            if(grantResult[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"Permission granted!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Permission not granted!",Toast.LENGTH_SHORT).show();
                 finish();
            }
        }
    }

    public static Bitmap getScreenShot(View view){
        View screenView=view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap=Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public void store(Bitmap bm,String fileName){
        String dirPath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Myfiles";
        File dir=new File(dirPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file = new File(dirPath,fileName);
        try{
            FileOutputStream fos=new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG,100,fos);
            fos.flush();
            fos.close();
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
        }
    }
}
