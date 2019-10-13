package com.example.project250;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArFragment arFragment;
    private ModelRenderable bearRenderable,
    catRenderable,cowRenderable,
    dogRenderable,elephantRenderable;

    ImageView bear,cat,cow,dog,elephant;

    View arrayView[];
    ViewRenderable name_animal;

    int selected=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    arFragment=(ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

    //view

        bear=(ImageView)findViewById(R.id.bear);
        cat=(ImageView)findViewById(R.id.cat);
        cow=(ImageView)findViewById(R.id.cow);
        dog=(ImageView)findViewById(R.id.dog);
        elephant=(ImageView)findViewById(R.id.elephant);

            setArrayView();
            setClickListener();
            setupModel();
            
            
            
            
            
            arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
                @Override
                public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {

                        Anchor anchor=hitResult.createAnchor();
                        AnchorNode anchorNode=new AnchorNode(anchor);
                        anchorNode.setParent(arFragment.getArSceneView().getScene());
                        createModel(anchorNode,selected);


                }
            });

    }

    private void setupModel() {

        ModelRenderable.builder()
                .setSource(this,R.raw.bear)
                .build().thenAccept(renderable -> bearRenderable=renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(this, "unable to load bear", Toast.LENGTH_SHORT).show();
            return null;
    }
        );


        ModelRenderable.builder()
                .setSource(this,R.raw.cat)
                .build().thenAccept(renderable -> catRenderable=renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "unable to load cat", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );


        ModelRenderable.builder()
                .setSource(this,R.raw.cow)
                .build().thenAccept(renderable -> cowRenderable=renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "unable to load cow", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );



        ModelRenderable.builder()
                .setSource(this,R.raw.dog)
                .build().thenAccept(renderable -> dogRenderable=renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "unable to load dogr", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

        ModelRenderable.builder()
                .setSource(this,R.raw.elephant)
                .build().thenAccept(renderable -> elephantRenderable=renderable)
                .exceptionally(throwable -> {
                            Toast.makeText(this, "unable to load elephant", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

    }

    private void createModel(AnchorNode anchorNode, int selected) {

        if(selected==1)
        {
            TransformableNode bear=new TransformableNode(arFragment.getTransformationSystem());
            bear.setParent(anchorNode);
            bear.setRenderable(bearRenderable);
            bear.select();

        }

    }

    private void setClickListener() {
        for(int i=0;i<arrayView.length;i++)
        arrayView[i].setOnClickListener(this);
    }

    private void setArrayView() {
        arrayView=new View[]{

          bear,cat,cow,dog,elephant
        };

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bear){
            selected=1;
            setBackground(v.getId());
        }
        else if(v.getId()==R.id.cat){
            selected=2;
            setBackground(v.getId());

        }
        else if(v.getId()==R.id.cow){
            selected=3;
            setBackground(v.getId());

        }
        else if(v.getId()==R.id.dog){
            selected=4;
            setBackground(v.getId());

        }
        else if(v.getId()==R.id.elephant){
            selected=5;
            setBackground(v.getId());

        }




    }

    private void setBackground(int id){
        for(int i=0;i<arrayView.length;i++)
        {
            if(arrayView[i].getId()==id)
            arrayView[i].setBackgroundColor(Color.parseColor("#80333639"));
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);


        }

    }

}
