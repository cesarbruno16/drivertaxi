package com.ride.taxiDriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 9/21/2017.
 */

public class Driver_Category_Adapter extends BaseAdapter {

        Context context;
        ArrayList<String> driver_arr;


        public Driver_Category_Adapter(Context context, ArrayList<String> driver_arr) {
            this.context = context;
            this.driver_arr = driver_arr;
        }

        @Override
        public int getCount() {
            return driver_arr.size();
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
                convertView = LayoutInflater.from(context).inflate(R.layout.itemlayoutforcars, parent, false);
                myHolder = new MyHolder(convertView);
                convertView.setTag(myHolder);
            } else {
                myHolder = (MyHolder) convertView.getTag();
            }

            myHolder.tv_car1.setText(driver_arr.get(position));
           /* if (language_id.equals("1")) {
                myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarTypeName());
            } else if (language_id.equals("2")) {
                myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarTypeNameFrench());
            } else if (language_id.equals("3")) {
                myHolder.tv_car1.setText(viewCarType.getMsg().get(position).getCarNameArabic());
            }
*/
            return convertView;
        }

        static class MyHolder {
            @Bind(R.id.tv_car1)
            TextView tv_car1;

            public MyHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }