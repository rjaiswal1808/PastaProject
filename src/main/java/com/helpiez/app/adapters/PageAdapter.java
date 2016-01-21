package com.helpiez.app.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.helpiez.app.ui.fragment.ActiveEventFragment;
import com.helpiez.app.ui.fragment.CompletedEventFragment;
import com.helpiez.app.ui.fragment.UpcomingEventFragment;

/**
 * Created by admin on 13/01/16.
 */
public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ActiveEventFragment activeEventFragment = new ActiveEventFragment();
                return activeEventFragment;
            case 1:
                CompletedEventFragment completedEventFragment = new CompletedEventFragment();
                return completedEventFragment;
            case 2:
                UpcomingEventFragment upcomingEventFragment = new UpcomingEventFragment();
                return upcomingEventFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
