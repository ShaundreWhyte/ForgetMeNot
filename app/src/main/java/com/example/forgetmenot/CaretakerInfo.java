package com.example.forgetmenot;

import java.util.ArrayList;

public class CaretakerInfo {
    public String name;
    public ArrayList<String> connectedPatientIds;

    public CaretakerInfo() {
        connectedPatientIds = new ArrayList<>();
    }
}
