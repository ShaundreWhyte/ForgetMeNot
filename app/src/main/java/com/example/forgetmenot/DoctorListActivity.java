package com.example.forgetmenot;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Button;

import com.example.forgetmenot_softwaredev.R;

public class DoctorListActivity extends AppCompatActivity {

    private DBManager dbManager;

    private Button btnAdd;

    private ListView listView;

    private SimpleCursorAdapter adapter;

    final String[] from = new String[] { DatabaseHelper._ID,
            DatabaseHelper.NAME, DatabaseHelper.ADDRESS, DatabaseHelper.EMAIL, DatabaseHelper.PHONE };

    final int[] to = new int[] { R.id.id, R.id.name, R.id.address, R.id.email, R.id.phone  };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_doctor_list);

        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_doctor, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.id);
                TextView nameTextView = (TextView) view.findViewById(R.id.name);
                TextView addressTextView = (TextView) view.findViewById(R.id.address);
                TextView emailTextView = (TextView) view.findViewById(R.id.email);
                TextView phoneTextView = (TextView) view.findViewById(R.id.phone);


                String id = idTextView.getText().toString();
                String name = nameTextView.getText().toString();
                String address = addressTextView.getText().toString();
                String email = emailTextView.getText().toString();
                String phone = phoneTextView.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModifyDoctorActivity.class);
                modify_intent.putExtra("name", name);
                modify_intent.putExtra("address", address);
                modify_intent.putExtra("email", email);
                modify_intent.putExtra("phone", phone);
                modify_intent.putExtra("id", id);

                startActivity(modify_intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent add_mem = new Intent(com.example.forgetmenot.DoctorListActivity.this, AddDoctorActivity.class);
                    startActivity(add_mem);

                }

        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.add_record) {

            Intent add_mem = new Intent(com.example.forgetmenot.DoctorListActivity.this, AddDoctorActivity.class);
            startActivity(add_mem);

        }
        return super.onOptionsItemSelected(item);
    }

}
