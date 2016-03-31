package com.helpiez.app.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.helpiez.app.R;
import com.helpiez.app.SessionManager;
import com.helpiez.app.model.ApplyResponse;
import com.helpiez.app.model.BusinessObject;
import com.helpiez.app.model.Event;
import com.helpiez.app.model.EventDetail;
import com.helpiez.app.model.User;
import com.helpiez.app.volley.FeedManager;
import com.helpiez.app.volley.FeedParams;
import com.helpiez.app.volley.Interfaces;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailActivity extends AppCompatActivity {

    EventDetail event;
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
        event = (EventDetail) getIntent().getExtras().getSerializable("EVENT_DETAIL");
        setSupportActionBar(toolbar);
        Log.e("Rahul", event.getName() + " : " + event.getCause());
        eventTitle.setText(null);
        eventTitle.setText(event.getName());
        eventCause.setText(event.getCause());
        eventDesc.setText(event.getAbout());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Rahul", "Clicked");
                String url = "http://rahuljaiswal.me/api/applyevent.php";
                HashMap<String, String> hmpRegType = new HashMap<>();
                hmpRegType.put("event_id", event.getId());
                hmpRegType.put("user_id", SessionManager.getUserId(EventDetailActivity.this));
                FeedParams feedParams = new FeedParams(url, ApplyResponse.class, new Interfaces.IDataRetrievalListener() {
                    @Override
                    public void onDataRetrieved(BusinessObject businessObject) {
                        Log.e("Rahul", "onDataRetrieved "+Boolean.valueOf(businessObject instanceof ApplyResponse));
                        if(businessObject != null && businessObject instanceof ApplyResponse){
                            ApplyResponse response = (ApplyResponse) businessObject;
                            Log.e("Rahul", response.getStatus()+"");
                            if(response.getStatus() == 1){
                                Toast.makeText(EventDetailActivity.this, "Applied Successfully", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(EventDetailActivity.this, "Server Error", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
                feedParams.setShouldCache(true);
                feedParams.setMethod(Request.Method.POST);
                feedParams.setPostParams(hmpRegType);
                FeedManager feedManager = new FeedManager();
                feedManager.queueJob(feedParams);
            }
        });
    }

}
