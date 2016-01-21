// Generated code from Butter Knife. Do not modify!
package com.helpiez.app;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CreateEvent$$ViewBinder<T extends com.helpiez.app.CreateEvent> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493004, "field 'mEventName'");
    target.mEventName = finder.castView(view, 2131493004, "field 'mEventName'");
    view = finder.findRequiredView(source, 2131493008, "field 'mEventCause'");
    target.mEventCause = finder.castView(view, 2131493008, "field 'mEventCause'");
    view = finder.findRequiredView(source, 2131492987, "field 'mProgressView'");
    target.mProgressView = view;
    view = finder.findRequiredView(source, 2131492988, "field 'mLoginFormView'");
    target.mLoginFormView = view;
    view = finder.findRequiredView(source, 2131492983, "field 'toolBar'");
    target.toolBar = finder.castView(view, 2131492983, "field 'toolBar'");
    view = finder.findRequiredView(source, 2131492984, "method 'fab'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.fab(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mEventName = null;
    target.mEventCause = null;
    target.mProgressView = null;
    target.mLoginFormView = null;
    target.toolBar = null;
  }
}
