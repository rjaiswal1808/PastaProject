package com.helpiez.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.helpiez.app.R;
import com.helpiez.app.SessionManager;
import com.helpiez.app.model.BusinessObject;
import com.helpiez.app.model.User;
import com.helpiez.app.volley.FeedManager;
import com.helpiez.app.volley.FeedParams;
import com.helpiez.app.volley.Interfaces;
import com.helpiez.app.volley.Volleyton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // UI references.
    @Bind(R.id.email)
    EditText mEmailView;

    @Bind(R.id.password)
    EditText mPasswordView;

    @Bind(R.id.login_progress)
    View mProgressView;

    @Bind(R.id.login_form)
    View mLoginFormView;

    @Bind(R.id.toolbar)
    Toolbar toolBar;

    @Bind(R.id.spinner)
    Spinner spinner;

    String loginAs;

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, LoginActivity.class);
//        //noinspection unchecked
//        ActivityCompat.startActivity(activity,
//                starter,
//                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
        activity.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Volleyton.createInstance(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(LoginActivity.this);
        setSupportActionBar(toolBar);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("NSS");
        categories.add("Volunteer");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

    }

    @OnClick(R.id.email_sign_in_button)
    public void email_sign_in_button() {
        attemptLogin();
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
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

            HashMap<String, String> hmpRegType = new HashMap<>();
            hmpRegType.put("email", email);
            hmpRegType.put("password", password);

            String url = "";
            if(loginAs.equalsIgnoreCase("NSS"))
                url = "http://rahuljaiswal.me/api/ngologin.php";
            else
                url = "http://rahuljaiswal.me/api/userlogin.php";

            FeedParams feedParams = new FeedParams(url, User.class, new Interfaces.IDataRetrievalListener() {
                @Override
                public void onDataRetrieved(BusinessObject businessObject) {
                    if(businessObject != null && businessObject instanceof User){
                        User user = (User) businessObject;
                        if(user.getStatus() == 1){
                            //do your work here
                            SessionManager.setSessionId(LoginActivity.this, user.getSessionId());
                            SessionManager.setUserType(LoginActivity.this, loginAs);
                            SessionManager.setUserId(LoginActivity.this, email);
                            SessionManager.setNssId(LoginActivity.this, user.getUserDetail().getNssId());
                            LandingActivity.start(LoginActivity.this);
                            finish();
                        }
                        else {
//                        // Login failed. Look at the ParseException to see what happened.
                            mPasswordView.setError("User name or password is invalid");
                            mPasswordView.requestFocus();
                        }
                        showProgress(false);
                    }
                }
            });
            feedParams.setShouldCache(true);
            feedParams.setMethod(Request.Method.POST);
            feedParams.setPostParams(hmpRegType);
            FeedManager feedManager = new FeedManager();
            feedManager.queueJob(feedParams);

//            ParseUser.logInInBackground(email, password, new LogInCallback() {
//                public void done(ParseUser user, ParseException e) {
//
//                    if (user != null) {
//                        // Hooray! The user is logged in
//                        LandingActivity.start(LoginActivity.this);
//                        finish();
//                    } else {
//                        // Login failed. Look at the ParseException to see what happened.
//                        mPasswordView.setError("User name or password is invalid");
//                        mPasswordView.requestFocus();
//                    }
//                    showProgress(false);
//                }
//
//            });
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_register:
                RegisterActivity.start(LoginActivity.this);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        loginAs = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Do Nothing
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
//    }

}

