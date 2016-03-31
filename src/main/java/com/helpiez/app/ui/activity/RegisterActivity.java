package com.helpiez.app.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.helpiez.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity {

    // UI references.

    @Bind(R.id.name)
    EditText mName;

    @Bind(R.id.email)
    EditText mEmailView;

    @Bind(R.id.phone_number)
    EditText mPhoneNumber;

    @Bind(R.id.password)
    EditText mPasswordView;

    @Bind(R.id.ngo_id)
    EditText mNssId;

    @Bind(R.id.login_progress)
    View mProgressView;

    @Bind(R.id.login_form)
    View mLoginFormView;

    public static void start(Activity activity) {
        Intent starter = new Intent(activity, RegisterActivity.class);
        //noinspection unchecked
        ActivityCompat.startActivity(activity,
                starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
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

    }

    @OnClick(R.id.email_sign_in_button)
    public void email_sign_in_button() {
        attemptLogin();
    }

    private void attemptLogin() {
        // Reset errors.
        mName.setError(null);
        mEmailView.setError(null);
        mPhoneNumber.setError(null);
        mPasswordView.setError(null);
        mNssId.setError(null);

        // Store values at the time of the login attempt.
        String ngo_name = mName.getText().toString();
        String email = mEmailView.getText().toString();
        String phoneNumber = mPhoneNumber.getText().toString();
        String password = mPasswordView.getText().toString();
//        String mNssId = mNssId.getText().toString();


        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(ngo_name) && !isNGONameValid(ngo_name)) {
            mName.setError(getString(R.string.error_invalid_ngoname));
            focusView = mName;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (!TextUtils.isEmpty(phoneNumber) && !isPhoneNumberValid(phoneNumber)) {
            mPhoneNumber.setError(getString(R.string.error_invalid_phonenumber));
            focusView = mPhoneNumber;
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

//            ParseObject parseObject = new ParseObject("nss");
//            parseObject.put("name", ngo_name);
//            parseObject.put("username", email);
//            parseObject.put("phoneNum", phoneNumber);
//            parseObject.put("email", email);
//            parseObject.put("password", password);
//            parseObject.saveInBackground();
//
//            ParseUser user = new ParseUser();
//            user.setUsername(ngo_name);
//            user.setPassword(password);
//            user.setEmail(email);
//
//            // other fields can be set just like with ParseObject
//            user.put("phone_number", phoneNumber);
//
//            user.signUpInBackground(new SignUpCallback() {
//                public void done(ParseException e) {
//                    if (e == null) {
//                        // Hooray! Let them use the com.helpiez.app now.
//                        LandingActivity.start(RegisterActivity.this);
//                    } else {
//                        // Sign up didn't succeed. Look at the ParseException
//                        // to figure out what went wrong
//                        showProgress(false);
//                        mPasswordView.setError("Registration failed please try again!");
//                        mNGOName.requestFocus();
//                    }
//                }
//            });
        }
    }

    private boolean isNGONameValid(String phoneNumber) {
        //TODO: Replace this with your own logic
        return phoneNumber.length() >= 4;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        //TODO: Replace this with your own logic
        return phoneNumber.length() == 10;
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

}

