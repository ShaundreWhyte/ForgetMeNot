package com.example.forgetmenot;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.forgetmenot_softwaredev.R;


public class OrganizationFragment extends Fragment {

    Button btnLogIn;
    EditText textEmail, textPassword;

    //temp
    Button btnSkip;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_organization, container, false);

        // Instantiate
        btnLogIn = (Button) rootView.findViewById(R.id.btnLogIn);
        textEmail = (EditText) rootView.findViewById(R.id.TextEmail);
        textPassword = (EditText) rootView.findViewById(R.id.TextPassword);

        // temp
        btnSkip = (Button) rootView.findViewById(R.id.btnSkipTest);

        textEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s != null) {
                    String inputEmail = s.toString();

                    // Compare to data from DB
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getContext(), DoctorListActivity.class);
                startActivity(intent1);
            }
        });

        return rootView;
    }
}