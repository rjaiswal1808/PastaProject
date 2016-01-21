// Generated code from Butter Knife. Do not modify!
package com.helpiez.app.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LandingActivity$$ViewBinder<T extends com.helpiez.app.ui.activity.LandingActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492984, "field 'fab'");
    target.fab = finder.castView(view, 2131492984, "field 'fab'");
    view = finder.findRequiredView(source, 2131492985, "field 'drawer'");
    target.drawer = finder.castView(view, 2131492985, "field 'drawer'");
    view = finder.findRequiredView(source, 2131492986, "field 'navigationView'");
    target.navigationView = finder.castView(view, 2131492986, "field 'navigationView'");
  }

  @Override public void unbind(T target) {
    target.fab = null;
    target.drawer = null;
    target.navigationView = null;
  }
}
