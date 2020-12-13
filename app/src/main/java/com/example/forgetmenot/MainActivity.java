package com.example.forgetmenot;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import com.example.forgetmenot_softwaredev.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate a ViewPager and a PagerAdapter
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new PatientFragment(), "Patient");
        adapter.addFragment(new DoctorFragment(), "Doctor");
        adapter.addFragment(new CaretakerLoginFragment(), "Care Taker");
        adapter.addFragment(new OrganizationFragment(), "Organization");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setBackgroundColor(0xff234233);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //AddNotification();
                Intent setAlarmIntent = new Intent(getApplicationContext(), NotificationView.class);
                startActivity(setAlarmIntent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        }
        else {
            // otherwise, select the previous step
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void AddNotification() {
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.drawable.ic_home_foreground)
//                        .setContentTitle("Notifications Example")
//                        .setContentText("This is a test notification");
//
//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);
//
//        // Add as notification
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.notify(0, builder.build());
    }

    public void OnCreateAlarm() {
        Intent myIntent = new Intent(getApplicationContext(), NotifyService.class ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE) ;
        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0 , myIntent , 0 ) ;
        Calendar calendar = Calendar. getInstance () ;
        calendar.set(Calendar. SECOND , 0 ) ;
        calendar.set(Calendar. MINUTE , 0 ) ;
        calendar.set(Calendar. HOUR , 0 ) ;
        calendar.set(Calendar. AM_PM , Calendar. AM ) ;
        calendar.add(Calendar. DAY_OF_MONTH , 1 ) ;
        alarmManager.setRepeating(AlarmManager. RTC_WAKEUP , calendar.getTimeInMillis() , 1000 * 60 * 60 * 24 , pendingIntent) ;
    }
}