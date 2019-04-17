package com.ride.taxiDriver.earnings;


import android.annotation.SuppressLint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ride.taxiDriver.R;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.newmodels.WeeklyEarningModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class FragmentGraph extends Fragment implements OnChartValueSelectedListener {

    protected BarChart mChart;

    ArrayList<String> arrayList;
    public static Float amount;
    Gson gson;
    TextView total_week_earn_txt;
    String current_date, next_date;
    ArrayList[] price_data;

    SessionManager sessionManager ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_graph, container, false);
        mChart = (BarChart) v.findViewById(R.id.chart1);

        sessionManager = new SessionManager(getContext());
        String strtext = getArguments().getString("name");
        total_week_earn_txt = (TextView) v.findViewById(R.id.total_week_earn_txt);

        arrayList = new ArrayList<>();
        arrayList.add("123");
        arrayList.add("1234");
        arrayList.add("12345");
        arrayList.add("123456");

        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);

        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(7);

        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        // mChart.setDrawYLabels(false);

        mChart.setDoubleTapToZoomEnabled(false);
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setDrawAxisLine(false);

        mChart.setOnChartValueSelectedListener(this);

        mChart.getAxisRight().setEnabled(false);

        mChart.getAxisLeft().setAxisLineColor(getActivity().getResources().getColor(R.color.icon_8_muted_black));
        mChart.getAxisLeft().setLabelCount(2);
        mChart.getAxisLeft().setTextColor(getActivity().getResources().getColor(R.color.icons_8_muted_grey));
        mChart.getAxisLeft().setTextSize(12f);
//        mChart.getAxisLeft().setTypeface(Typeface.createFromAsset(this.getAssets(), "Monix-Regular.otf"));
        mChart.getAxisLeft().setGridColor(getActivity().getResources().getColor(R.color.icon_8_muted_black));
        mChart.getAxisLeft().setValueFormatter(new MyAxisValueFormatter(sessionManager));

        mChart.setDrawGridBackground(false);
        mChart.getLegend().setEnabled(false);


        mChart.getXAxis().setDrawLabels(false);
        mChart.getXAxis().setAxisLineColor(getActivity().getResources().getColor(R.color.icon_8_muted_black));
        mChart.getXAxis().setGridColor(getActivity().getResources().getColor(R.color.icon_8_muted_black));


        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        XYMarkerView mv = new XYMarkerView(getActivity(), xAxisFormatter , sessionManager);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart


        gson = new GsonBuilder().create();
        WeeklyEarningModel weeklyEarningModel;
        weeklyEarningModel = gson.fromJson(getArguments().getString("response"), WeeklyEarningModel.class);

        total_week_earn_txt.setText(""+ sessionManager.getCurrencyCode() + String.valueOf(weeklyEarningModel.getWeekly_amount()));
//        Log.e("Amount", "" + weeklyEarningModel.getWeekly_amount());

        for (int i = 0; i < weeklyEarningModel.getDetails().size(); i++) {

            amount = Float.valueOf(weeklyEarningModel.getDetails().get(i).getDetail().getAmount().trim());
        }
        setData( amount, weeklyEarningModel);


        return v;
    }

    public static FragmentGraph newInstance(String text, String response) {

        FragmentGraph f = new FragmentGraph();
        Bundle b = new Bundle();
        b.putString("msg", text);
        b.putString("response", response);
        f.setArguments(b);

        return f;
    }


    @Override
    public void onNothingSelected() {

    }

    private void setData( float range, WeeklyEarningModel weeklyEarningModel) {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0 ; i <weeklyEarningModel.getDetails().size(); i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);

            yVals1.add(new BarEntry(i, Float.parseFloat(""+weeklyEarningModel.getDetails().get(i).getDetail().getAmount())));
        }



//        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
//
//        try {
//        } catch (Exception e) {
//            Log.e("Exception", "" + e);
//        }
//        for (int i = 0; i < weeklyEarningModel.getDetails().size(); i++) {
//
//            amount = Float.valueOf(weeklyEarningModel.getDetails().get(i).getDetails().getAmount().trim());
//
//        }
//        for (int i = 0; i < 7; i++) {
//            float mult = (range + 1);
//            float val = (float) (Math.random() * mult);
//            //  } else {
//            //      yVals1.add(new BarEntry(i, val));
//            //  }
//            Log.e("Final_amount", "" + amount);
//            //  if (Math.random() * 100 < 25) {
//            // yVals1.add(new BarEntry(i, 100 + i, getResources().getDrawable(R.drawable.ic_about_us)));
//            yVals1.add(new BarEntry(i, amount, getResources().getDrawable(R.drawable.ic_about_us)));
//
//        }

        BarDataSet set1;

        if (mChart.getData() != null &&
                mChart.getData().

                        getDataSetCount()

                        > 0)

        {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else

        {
            set1 = new BarDataSet(yVals1, "");
            set1.setDrawIcons(false);

            set1.setColors(getActivity().getResources().getColor(R.color.icons_8_muted_green_1_dark));

            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(mTfLight);
            data.setBarWidth(0.9f);
            data.setDrawValues(false);

            mChart.setData(data);
            mChart.animateY(1050);
        }

    }

    protected RectF mOnValueSelectedRectF = new RectF();

    @SuppressLint("NewApi")
    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }
}
