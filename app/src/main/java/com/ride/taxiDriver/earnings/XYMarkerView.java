package com.ride.taxiDriver.earnings;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.R;
import com.ride.taxiDriver.manager.SessionManager;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class XYMarkerView extends MarkerView {

    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;

    private DecimalFormat format;
    Context mContext ;
    SessionManager sessionManager ;

    public XYMarkerView(Context context, IAxisValueFormatter xAxisValueFormatter , SessionManager sessionManager ) {
        super(context, R.layout.custom_marker_view);

        mContext = context ;
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = (TextView) findViewById(R.id.tvContent);
        format = new DecimalFormat("###.0");
        this.sessionManager = sessionManager ;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

//        tvContent.setText("x: " + xAxisValueFormatter.getFormattedValue(e.getX(), null) + ", y: " + format.format(e.getY()));
        tvContent.setText(""+ format.format(e.getY())+""+sessionManager.getCurrencyCode());

        tvContent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Change to the second acticity", Toast.LENGTH_SHORT).show();
            }
        });
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}