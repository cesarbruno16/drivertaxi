package com.ride.taxiDriver.earnings;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class GraphFragmentAdapter extends FragmentPagerAdapter {

    String response;

    public GraphFragmentAdapter(FragmentManager supportFragmentManager, String response) {
        super(supportFragmentManager);
        this.response = response;
    }

    @Override
    public Fragment getItem(int pos) {

        Log.e("Response", "" + response);
        return FragmentGraph.newInstance("FragmentGraph, Default", response);
    }

    @Override
    public int getCount() {
        return 5;
    }
}