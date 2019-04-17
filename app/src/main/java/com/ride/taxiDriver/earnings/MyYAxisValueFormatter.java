package com.ride.taxiDriver.earnings;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by samirgoel3@gmail.com on 5/20/2017.
 */

public class MyYAxisValueFormatter  implements IValueFormatter {

    private DecimalFormat mFormat;

    public MyYAxisValueFormatter() {

        // format values to 1 decimal digit
        mFormat = new DecimalFormat("###,###,##0.0");
    }



    public int getDecimalDigits() { return 1; }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return mFormat.format(value) + " ₦";
    }
}