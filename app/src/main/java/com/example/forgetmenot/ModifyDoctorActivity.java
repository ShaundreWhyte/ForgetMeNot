package com.example.forgetmenot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.example.forgetmenot_softwaredev.R;

public class ModifyDoctorActivity extends Activity implements OnClickListener {

    private EditText nameText;
    private EditText addressText;
    private EditText emailText;
    private EditText phoneText;
    private Button updateBtn, deleteBtn;
    private long _id;
    //private String password;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify Record");

        setContentView(R.layout.activity_modify_doctor);

        dbManager = new DBManager(this);
        dbManager.open();

        nameText = (EditText) findViewById(R.id.name_edittext);
        addressText = (EditText) findViewById(R.id.address_edittext);
        emailText = (EditText) findViewById(R.id.email_edittext);
        phoneText = (EditText) findViewById(R.id.phone_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String address = intent.getStringExtra("address");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        //password = intent.getStringExtra(("password"));


        _id = Long.parseLong(id);

        nameText.setText(name);
        addressText.setText(address);
        emailText.setText(email);
        phoneText.setText(phone);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = nameText.getText().toString();
                String address = addressText.getText().toString();
                String email = emailText.getText().toString();
                String phone = phoneText.getText().toString();

                dbManager.update(_id, name, address, email, phone);
                this.returnHome();
                break;

            case R.id.btn_delete:
                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), DoctorListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
