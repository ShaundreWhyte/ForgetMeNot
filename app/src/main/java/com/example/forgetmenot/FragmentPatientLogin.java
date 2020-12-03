package com.example.forgetmenot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forgetmenot_softwaredev.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentPatientLogin extends Fragment {
    Button btnLogIn;
    TextView textSearchPassword, textCreateAccount;
    EditText textEmail, textPassword;
    LinearLayout linearLayout;
    Toolbar toolbar;

    // Temp Data
    ArrayList<ItemPatient> items = new ArrayList<ItemPatient>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_patient_login, container, false);

        // Instantiate
        btnLogIn = (Button) rootView.findViewById(R.id.btnLogIn);
        textEmail = (EditText) rootView.findViewById(R.id.TextEmail);
        textPassword = (EditText) rootView.findViewById(R.id.TextPassword);
        textCreateAccount = (TextView) rootView.findViewById(R.id.txtCreateAccount);
        textSearchPassword = (TextView) rootView.findViewById(R.id.txtSearchPW);

        // Check : data is correct or not
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

        // Temp Data
        // Set List & Adapter
        items = new ArrayList<ItemPatient>();
        items.add(new ItemPatient("Test", "1234"));
        items.add(new ItemPatient("Test2", "1234"));
        items.add(new ItemPatient("Test3", "1234"));
        items.add(new ItemPatient("Test4", "1234"));
        items.add(new ItemPatient("Test5", "1234"));

        btnLogIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textCreateAccount.setTextColor(getResources().getColor(R.color.lightGray));
                textSearchPassword.setTextColor(getResources().getColor(R.color.lightGray));

                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();

                if(ValidationCheck(email, password)) {
                    Toast.makeText(getActivity(), "Success to Login", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getActivity(), "Fail to Login", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(this, MainActivity.class);
//                intent.putExtra("email", email);
//                intent.putExtra("password", password);
//                intent.putExtra("role", rBtnRole);
//                startActivity(intent);

//                FragmentPatientList listFragment = new FragmentPatientList();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container, listFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

        textSearchPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textCreateAccount.setTextColor(getResources().getColor(R.color.lightGray));
                textSearchPassword.setTextColor(getResources().getColor(R.color.lightBlue));
                Toast.makeText(getContext(), "TestSearch: Click", Toast.LENGTH_SHORT).show();
            }
        });

        textCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearchPassword.setTextColor(getResources().getColor(R.color.lightGray));
                textCreateAccount.setTextColor(getResources().getColor(R.color.lightBlue));
                Toast.makeText(getContext(), "TestCreate click", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    public void AfterLogin(Bundle bundle) {
        if(bundle != null) {
            String email = bundle.getString("email");
            String password = bundle.getString("password");
            String role = bundle.getString("role");

            if(email != null && password != null) {
                //getSupportActionBar().setTitle("Test, Success Login");
                String testResult = String.format("%s : %s : %s", email, password, role);
                //Toast.makeText(this, testResult, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean ValidationCheck(String id, String password) {
        boolean result = true;

        for(int i = 0; i < items.size(); i++) {
            String compId = items.get(i).id;
            String compPw = items.get(i).password;

            if(compId.equals(id) && compPw.equals(password))
                result = true;
            else
                result = false;
        }

        return  result;
    }
}