package com.ride.taxiDriver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ride.taxiDriver.R;
import com.ride.taxiDriver.models.viewcity.ViewCity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CityAdapter extends BaseAdapter {
    Context context;
    ViewCity viewCity;

    public CityAdapter(Context context, ViewCity viewCity) {
        this.context = context;
        this.viewCity = viewCity;
    }

    @Override
    public int getCount() {
        return viewCity.getMsg().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHolder myHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforcities, parent, false);
            myHolder = new MyHolder(convertView);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.tv_city1.setText(viewCity.getMsg().get(position).getCityName());
        return convertView;
    }

    static class MyHolder {
        @Bind(R.id.tv_city1)
        TextView tv_city1;

        public MyHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
