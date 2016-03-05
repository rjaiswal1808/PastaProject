package com.helpiez.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.helpiez.app.R;
import com.helpiez.app.model.NGOEvent;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    NGOEvent event;
    @Bind(R.id.event_title)
    TextView eventTitle;
    @Bind(R.id.event_cause)
    TextView eventCause;
    @Bind(R.id.event_description)
    TextView eventDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail_sctivity);
        ButterKnife.bind(EventDetailActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        event = (NGOEvent) getIntent().getExtras().getSerializable("EVENT_DETAIL");
        setSupportActionBar(toolbar);
        Log.e("Rahul", event.getEventName()+" : " + event.getEventCause());
        eventTitle.setText(event.getEventName());
        eventTitle.setText(event.getEventCause());
        eventTitle.setText(event.getEventDescription());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
