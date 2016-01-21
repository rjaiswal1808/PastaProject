package com.helpiez.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.helpiez.app.R;

import butterknife.Bind;
import butterknife.OnClick;

public class EventCreationActivity extends AppCompatActivity {

    @Bind(R.id.event_name)
    EditText eventName;

    @Bind(R.id.event_start_date_time)
    EditText eventStart;

    @Bind(R.id.event_end_date_time)
    EditText eventEnd;

    @Bind(R.id.event_description)
    EditText eventDescription;

    @Bind(R.id.event_cause)
    EditText eventCause;

    @Bind(R.id.event_location)
    EditText eventLocation;

    @Bind(R.id.btn_create_event)
    Button createEvent;

    @OnClick(R.id.btn_create_event)
    public void createEvent() {
        eventName.setError(null);
        eventStart.setError(null);
        eventEnd.setError(null);
        eventDescription.setError(null);
        eventCause.setError(null);
        eventLocation.setError(null);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
