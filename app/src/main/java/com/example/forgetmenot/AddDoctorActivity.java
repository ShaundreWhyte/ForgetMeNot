package com.example.forgetmenot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.forgetmenot_softwaredev.R;

public class AddDoctorActivity extends Activity implements OnClickListener {

    private Button addTodoBtn;
    private EditText nameEditText;
    private EditText addressEditText;
    private EditText emailEditText;
    private EditText phoneEditText;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");

        setContentView(R.layout.activity_add_doctor);

        nameEditText = (EditText) findViewById(R.id.name_edittext);
        addressEditText = (EditText) findViewById(R.id.address_edittext);
        emailEditText = (EditText) findViewById(R.id.email_edittext);
        phoneEditText = (EditText) findViewById(R.id.phone_edittext);


        addTodoBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = nameEditText.getText().toString();
                final String address = addressEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String phone = phoneEditText.getText().toString();
                final String password = name+1;

                dbManager.insert(name, address, email, phone);

                Intent main = new Intent(com.example.forgetmenot.AddDoctorActivity.this, DoctorListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }

}
