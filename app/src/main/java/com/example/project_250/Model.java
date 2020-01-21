package com.example.project_250;

import com.google.ar.core.Plane;

public class Model {
    private int modelSfbRes;
    private Integer modelSfbRes2;
    private String displayableName;
    private Plane.Type planeType;
    public Model(int modelSfbRes, String displayableName, Plane.Type planeType) {
        this.modelSfbRes = modelSfbRes;
        this.displayableName = displayableName;
        this.planeType = planeType;
    }

    public int getModelSfbRes() {
        return modelSfbRes;
    }

    public String getDisplayableName() {
        return displayableName;
    }

    public Integer getModelSfbRes2() {
        return modelSfbRes2;
    }

    public Plane.Type getPlaneType() {
        return planeType;
    }
}