// Generated code from Butter Knife. Do not modify!
package com.helpiez.app.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class RegisterActivity$$ViewBinder<T extends com.helpiez.app.ui.activity.RegisterActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492995, "field 'mNGOName'");
    target.mNGOName = finder.castView(view, 2131492995, "field 'mNGOName'");
    view = finder.findRequiredView(source, 2131492990, "field 'mEmailView'");
    target.mEmailView = finder.castView(view, 2131492990, "field 'mEmailView'");
    view = finder.findRequiredView(source, 2131492996, "field 'mPhoneNumber'");
    target.mPhoneNumber = finder.castView(view, 2131492996, "field 'mPhoneNumber'");
    view = finder.findRequiredView(source, 2131492991, "field 'mPasswordView'");
    target.mPasswordView = finder.castView(view, 2131492991, "field 'mPasswordView'");
    view = finder.findRequiredView(source, 2131492987, "field 'mProgressView'");
    target.mProgressView = view;
    view = finder.findRequiredView(source, 2131492988, "field 'mLoginFormView'");
    target.mLoginFormView = view;
    view = finder.findRequiredView(source, 2131492993, "method 'email_sign_in_button'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.email_sign_in_button();
        }
      });
  }

  @Override public void unbind(T target) {
    target.mNGOName = null;
    target.mEmailView = null;
    target.mPhoneNumber = null;
    target.mPasswordView = null;
    target.mProgressView = null;
    target.mLoginFormView = null;
  }
}
