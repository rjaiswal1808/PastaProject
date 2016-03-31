package com.helpiez.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.helpiez.app.R;
import com.helpiez.app.SessionManager;
import com.helpiez.app.model.BusinessObject;
import com.helpiez.app.model.CreatedEvent;
import com.helpiez.app.model.EventDetail;
import com.helpiez.app.model.EventsList;
import com.helpiez.app.volley.FeedManager;
import com.helpiez.app.volley.FeedParams;
import com.helpiez.app.volley.Interfaces;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventCreationActivity extends AppCompatActivity {

    private static int IS_START_DATE_CLICKED = 0;

    @Bind(R.id.event_name)
    EditText eventName;

    @Bind(R.id.event_start_date_time)
    EditText eventStart;

    @OnClick(R.id.event_start_date_time)
    public void startDateClicked() {
        IS_START_DATE_CLICKED = 1;
        showDatePickerDialog();
    }

    @Bind(R.id.event_end_date_time)
    EditText eventEnd;

    @OnClick(R.id.event_end_date_time)
    public void endDateClicked() {
        IS_START_DATE_CLICKED = 0;
        showDatePickerDialog();
    }

    @Bind(R.id.event_description)
    EditText eventDescription;

    @Bind(R.id.event_cause)
    EditText eventCause;

    @Bind(R.id.event_location)
    EditText eventLocation;

    @Bind(R.id.login_progress)
    View mProgressView;

    @Bind(R.id.event_creation_form)
    View mEventCreationFormView;

    @Bind(R.id.btn_create_event)
    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_creation);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.btn_create_event)
    public void createEventClicked(){
        createEvent();
    }

    public void createEvent() {
        eventName.setError(null);
        eventStart.setError(null);
        eventEnd.setError(null);
        eventDescription.setError(null);
        eventCause.setError(null);
        eventLocation.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(eventName.getText())) {
            eventName.setError(getString(R.string.error_invalid_event_name));
            focusView = eventName;
            cancel = true;
        }
        if (TextUtils.isEmpty(eventStart.getText())) {
            eventStart.setError(getString(R.string.error_invalid_event_start));
            focusView = eventStart;
            cancel = true;
        }
        if (TextUtils.isEmpty(eventEnd.getText())) {
            eventEnd.setError(getString(R.string.error_invalid_event_end));
            focusView = eventEnd;
            cancel = true;
        }
        if (TextUtils.isEmpty(eventDescription.getText())) {
            eventDescription.setError(getString(R.string.error_invalid_event_desc));
            focusView = eventDescription;
            cancel = true;
        }
        if (TextUtils.isEmpty(eventCause.getText())) {
            eventCause.setError(getString(R.string.error_invalid_event_cause));
            focusView = eventCause;
            cancel = true;
        }
        if (TextUtils.isEmpty(eventLocation.getText())) {
            eventLocation.setError(getString(R.string.error_invalid_event_loc));
            focusView = eventLocation;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

//            NGOEvent event = new NGOEvent();
//            event.setEventName(eventName.getText().toString());
//            event.setEventTime(eventStart.getText().toString());
//            event.setEventDeadline(eventEnd.getText().toString());
//            event.setEventDescription(eventDescription.getText().toString());
//            event.setEventCause(eventCause.getText().toString());
//            event.setEventLocationAddress(eventLocation.getText().toString());

            HashMap<String, String> hmpRegType = new HashMap<>();
            hmpRegType.put("name", eventName.getText().toString());
            hmpRegType.put("about", eventDescription.getText().toString());
            hmpRegType.put("when", eventStart.getText().toString());
            hmpRegType.put("where", eventLocation.getText().toString());
            hmpRegType.put("cause", eventCause.getText().toString());
            hmpRegType.put("reimbursement", "NA");
            hmpRegType.put("qual", "NA");
            hmpRegType.put("req", "NA");
            hmpRegType.put("deadline", eventEnd.getText().toString());
            hmpRegType.put("ngo", "Helpiez NGO");
            hmpRegType.put("nss_id", SessionManager.getNssId(this));
            String url = "http://rahuljaiswal.me/api/createvent.php";

            FeedParams feedParams = new FeedParams(url, CreatedEvent.class, new Interfaces.IDataRetrievalListener() {
                @Override
                public void onDataRetrieved(BusinessObject businessObject) {
                    Log.e("Rahul", "businessObject = "+Boolean.valueOf(businessObject instanceof CreatedEvent));
                    if(businessObject != null && businessObject instanceof CreatedEvent){
                        CreatedEvent event = (CreatedEvent) businessObject;
                        if(event.getStatus() == 1){
                            Toast.makeText(EventCreationActivity.this, "New Event Created. Event Id : "+event.getId(), Toast.LENGTH_LONG);
                        }
                        else {
                            Log.e("Rahul", "No Response");
                        }
                    }
                    showProgress(false);
                }
            });
            feedParams.setShouldCache(true);
            feedParams.setMethod(Request.Method.POST);
            feedParams.setPostParams(hmpRegType);
            FeedManager feedManager = new FeedManager();
            feedManager.queueJob(feedParams);

        }
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mEventCreationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mEventCreationFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mEventCreationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mEventCreationFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:

                // open datepicker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                final Calendar c = Calendar.getInstance();
                return new DatePickerDialog(this, pickerListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    public void showDatePickerDialog() {
        showDialog(0);
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            // Show selected date
            selectedMonth = selectedMonth + 1;
            String day, month;
            if(selectedDay < 10)
                day = "0"+selectedDay;
            else
                day = ""+selectedDay;
            if(selectedMonth < 10)
                month = "0"+selectedMonth;
            else
                month = ""+selectedMonth;
            if(IS_START_DATE_CLICKED == 1)
                eventStart.setText(new StringBuilder().append(month)
                        .append("-").append(day).append("-").append(selectedYear)
                        .append(" "));
            else
                eventEnd.setText(new StringBuilder().append(month)
                        .append("-").append(day).append("-").append(selectedYear)
                        .append(" "));
        }
    };

}
