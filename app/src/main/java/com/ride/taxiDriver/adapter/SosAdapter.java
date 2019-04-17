package com.ride.taxiDriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ride.taxiDriver.R;
import com.ride.taxiDriver.models.NewSosModel;

/**
 * Created by samirgoel3@gmail.com on 8/24/2017.
 */

public class SosAdapter extends BaseAdapter {

    NewSosModel mSosResponse ;
    Context context ;

    public SosAdapter(Context context , NewSosModel response ){
        this.context = context ;
        this.mSosResponse = response ;
    }


    @Override
    public int getCount() {
        return mSosResponse.getDetails().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_for_sos, parent, false);
        TextView phone_number = (TextView) convertView.findViewById(R.id.phone_number);
        TextView service_name_txt = (TextView) convertView.findViewById(R.id.service_name_txt);

         phone_number.setText(""+mSosResponse.getDetails().get(i).getSos_number());
        service_name_txt.setText(""+mSosResponse.getDetails().get(i).getSos_name());
        return convertView;
    }

}