package com.ride.taxiDriver.earnings;

import com.ride.taxiDriver.manager.SessionManager;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

public class MyAxisValueFormatter implements IAxisValueFormatter
{

    private DecimalFormat mFormat;
    SessionManager sessionManager ;

    public MyAxisValueFormatter(SessionManager sessionManager) {
        this.sessionManager = sessionManager ;
        mFormat = new DecimalFormat("###,###,###,##0.0");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis ) {
        return mFormat.format(value) + " "+ sessionManager.getCurrencyCode();
    }
}