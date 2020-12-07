package com.example.forgetmenot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.forgetmenot_softwaredev.R;

public class CareTakerFragment extends Fragment implements View.OnClickListener{
    CaretakerInfo caretaker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_care_taker, container, false);

        boolean isLoggedOn = true;
        // check login
        isLoggedOn = checkCaretakerLogin();
        if(isLoggedOn) {
            // if logged in show caretaker info
        }
        else {
            // if not, go to login
            // Todo: if user has not logged in place login routine here
            LoginCaretaker();
        }

        Button button_update = view.findViewById(R.id.btn_caretaker_updateinfo);
        button_update.setOnClickListener(this);
        Button button_register = view.findViewById(R.id.btn_caretaker_register_to_patient);
        button_register.setOnClickListener(this);
        Button button_update_patient = view.findViewById(R.id.btn_caretaker_update_patientinfo);
        button_update_patient.setOnClickListener(this);

        return view;
    }

    // Todo: place caretaker login here
    private boolean checkCaretakerLogin() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_caretaker_updateinfo:
                break;
            case R.id.btn_caretaker_register_to_patient:
                Log.i("FMN", "call patientlist_activity");
                Intent intent = new Intent(getContext(), PatientList_Activity.class);
                intent.putExtra("ListType", 2);
                intent.putExtra("Caretaker", caretaker.name);
                startActivity(intent);
                break;
            case R.id.btn_caretaker_update_patientinfo:
                Intent intent1 = new Intent(getContext(), PatientList_Activity.class);
                intent1.putExtra("ListType", 3);
                intent1.putExtra("Caretaker", caretaker.name);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    private void LoginCaretaker() {
        // Todo: log in caretaker and set information
        caretaker = new CaretakerInfo();
        caretaker.name = "caretaker1";
        caretaker.connectedPatientIds.clear();
    }
}
