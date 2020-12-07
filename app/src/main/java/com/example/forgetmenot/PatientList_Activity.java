package com.example.forgetmenot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.forgetmenot_softwaredev.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PatientList_Activity extends AppCompatActivity {

    private RecyclerView patientListView;
    private PatientListRecyclerAdapter viewAdapter;
    private int listType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_list_activity);

        Log.i("aaa", "onCreate");
        TextView title = findViewById(R.id.tv_patientlist_title);
        Intent intent = getIntent();
        listType = intent.getIntExtra("ListType", 0);
        String caretaker_name = intent.getStringExtra("Caretaker");
        String titleText = "Patient list";
        Log.i("aaa", "setTitle");
        switch (listType) {
            case 2:
                titleText = String.format("Select patients to connect to caretaker %s", caretaker_name);
                break;
            case 3:
                titleText = String.format("Select patient to update information");
                break;
            default:
                break;
        }
        title.setText(titleText);

        Log.i("aaa", "set list");
        patientListView = findViewById(R.id.patientlist_recyclerview);
        patientListView.setLayoutManager(new LinearLayoutManager(this));
        patientListView.setHasFixedSize(true);
        viewAdapter = new PatientListRecyclerAdapter(getPatientList());
        patientListView.setAdapter(viewAdapter);

        Button confirm = findViewById(R.id.btn_patientlist_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConfirmAction();
                finish();
            }
        });
    }

    private ArrayList<ItemPatient> getPatientList() {
        // Todo: get patient list from db or something
        ArrayList<ItemPatient> patientList = new ArrayList<>();
        if(listType == 3) {
            // Todo: get patient list connected to caretaker from db or something
            // make a list of patients
        }
        else {
            patientList.add(new ItemPatient("Test 1", "TestA", "M", "25", "Nov 25, 2020"));
            patientList.add(new ItemPatient("Test 2", "TestB", "F", "30", "Dec 20, 2020"));
            patientList.add(new ItemPatient("Test 3", "TestC", "M", "50", "Nov 30, 2020"));
            patientList.add(new ItemPatient("Test 4", "TestD", "F", "40", "Dec 10, 2020"));
            patientList.add(new ItemPatient("Test 5", "TestE", "M", "15", "Dec 12, 2020"));
        }
        return patientList;
    }

    private void connectCaretakerToPatient() {
        // update db or something to connect caretaker to selected patients
        ArrayList<ItemPatient> selectedPatients = viewAdapter.getCheckedItems();
    }

    private void onConfirmAction() {
        switch (listType) {
            case 2:
                break;
            case 3:
                // Todo: change checkbox list to radio list to select only one
                ArrayList<ItemPatient> selectedPatients = viewAdapter.getCheckedItems();
                ItemPatient patient = null;
                if(selectedPatients.size() > 0)
                    patient = selectedPatients.get(0);
                // Todo: show patient information update screen (use the one which is used to update patient info by patients)
                break;
        }
    }
}