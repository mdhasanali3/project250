package com.example.project250;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    ArFragment arFragment;
    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;

    private Button btnremove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    arFragment=(ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);

    btnremove=(Button)findViewById(R.id.remove);

    getimage();


           arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
               Anchor anchor=hitResult.createAnchor();
               AnchorNode anchorNode=new AnchorNode(anchor);
               ModelRenderable.builder()
                       .setSource(this, Uri.parse(common.model))
                       .build()
                       .thenAccept(modelRenderable -> createModel(anchorNode,modelRenderable));

           });
btnremove.setOnClickListener(view->removeAnchorNode(anchorNode));
    }

    private void getimage() {

        imagesPath.add(R.drawable.table);
        imagesPath.add(R.drawable.bookshelf);
        imagesPath.add(R.drawable.lamp);
        imagesPath.add(R.drawable.odltv);
        imagesPath.add(R.drawable.clothdryer);
        imagesPath.add(R.drawable.chair);
        namesPath.add("Table");
        namesPath.add("BookShelf");
        namesPath.add("Lamp");
        namesPath.add("Old Tv");
        namesPath.add("Cloth Dryer");
        namesPath.add("Chair");
        modelNames.add("table.sfb");
        modelNames.add("model.sfb");
        modelNames.add("lamp.sfb");
        modelNames.add("tv.sfb");
        modelNames.add("cloth.sfb");
        modelNames.add("chair.sfb");

        initaiteRecyclerview();

    }


    private void initaiteRecyclerview() {

         LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,namesPath,imagesPath,modelNames);
        recyclerView.setAdapter(adapter);

    }


    private void createModel(AnchorNode anchorNode, ModelRenderable modelRenderable) {


            TransformableNode model=new TransformableNode(arFragment.getTransformationSystem());
            model.setParent(anchorNode);
            model.setRenderable(modelRenderable);
            arFragment.getArSceneView().getScene().addChild(anchorNode);
            model.select();


    }


    private void removeAnchorNode(AnchorNode anchorNode) {
        if(anchorNode!=null){
            arFragment.getArSceneView().getScene().removeChild(anchorNode);
            anchorNode.getAnchor().detach();
            anchorNode.setParent(null);
            anchorNode=null;
        }

    }


}
