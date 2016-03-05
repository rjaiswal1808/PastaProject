// Generated code from Butter Knife. Do not modify!
package com.helpiez.app.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends com.helpiez.app.ui.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492990, "field 'mEmailView'");
    target.mEmailView = finder.castView(view, 2131492990, "field 'mEmailView'");
    view = finder.findRequiredView(source, 2131492991, "field 'mPasswordView'");
    target.mPasswordView = finder.castView(view, 2131492991, "field 'mPasswordView'");
    view = finder.findRequiredView(source, 2131492984, "field 'mProgressView'");
    target.mProgressView = view;
    view = finder.findRequiredView(source, 2131492988, "field 'mLoginFormView'");
    target.mLoginFormView = view;
    view = finder.findRequiredView(source, 2131492983, "field 'toolBar'");
    target.toolBar = finder.castView(view, 2131492983, "field 'toolBar'");
    view = finder.findRequiredView(source, 2131492994, "field 'facebookLoginButton'");
    target.facebookLoginButton = finder.castView(view, 2131492994, "field 'facebookLoginButton'");
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
    target.mEmailView = null;
    target.mPasswordView = null;
    target.mProgressView = null;
    target.mLoginFormView = null;
    target.toolBar = null;
    target.facebookLoginButton = null;
  }
}
