package com.ride.taxiDriver.earnings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ride.taxiDriver.Config;
import com.ride.taxiDriver.R;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.newmodels.WeeklyEarningModel;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeeklyStatementActivity extends Activity implements ApiManager.APIFETCHER {

    @Bind(R.id.trip_container)
    LinearLayout mTripContainer;
    @Bind(R.id.backBtnLL)
    LinearLayout backBtn;
    TextView totalEarningTV, completedTripsTV, fareEarnedTV, taxiFeeTV, bonusTV, textView4, weekDatesTV;
    String driver_id;
    ProgressDialog pd;
    ApiManager apiManager;
    SessionManager sessinManager;
    ArrayList<String> price_data_list;
    ArrayList<String> week_data_list;
    ArrayList<String> date_list;
    String date1;

    String incoming_date = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_statement);
        ButterKnife.bind(this);

        incoming_date = getIntent().getExtras().getString("date");
        initialization();
    }

    private void initialization() {

        apiManager = new ApiManager(this);
        price_data_list = new ArrayList<>();
        week_data_list = new ArrayList<>();
        date_list = new ArrayList<>();
        sessinManager = new SessionManager(WeeklyStatementActivity.this);
        driver_id = new SessionManager(WeeklyStatementActivity.this).getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        pd = new ProgressDialog(this);
        pd.setMessage("" + this.getResources().getString(R.string.loading));
        ButterKnife.bind(this);
//        for (int i = 0; i < week_data.length; i++) {
//            mTripContainer.addView(getTripItemView(week_data[i], price_data[i], this));
//        }


        totalEarningTV = (TextView) findViewById(R.id.totalEarningTV);
        completedTripsTV = (TextView) findViewById(R.id.completedTripsTV);
        fareEarnedTV = (TextView) findViewById(R.id.fareEarnedTV);
        taxiFeeTV = (TextView) findViewById(R.id.taxiFeeTV);
        bonusTV = (TextView) findViewById(R.id.bonusTV);
        textView4 = (TextView) findViewById(R.id.textView4);
        weekDatesTV = (TextView) findViewById(R.id.weekDatesTV);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeeklyStatementActivity.this.finish();
            }
        });
        weekelyEarningApi();

    }

    public View getTripItemView(String day_date, String amount, final String date, final Context context) {
        View child = getLayoutInflater().inflate(R.layout.week_day_item, null);
        final TextView day_date_txt = (TextView) child.findViewById(R.id.day_date_txt);
        TextView amount_txt = (TextView) child.findViewById(R.id.amount_txt);


        day_date_txt.setText("" + day_date);
        amount_txt.setText("" + amount);

        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("HeadDAte", "" + day_date_txt.getText().toString());
                Log.e("DateFinall", "" + date);


                startActivity(new Intent(context, DailyStatementActivity.class).putExtra("Date", date).putExtra("Head_date", day_date_txt.getText().toString()));

            }
        });
        return child;

    }

    private void weekelyEarningApi() {

        try {
            Log.e("Name", "" + Config.ApiKeys.KEY_WEEKLY_EARNING);
            Log.e("ApiName", "" + Apis.weeklyEarnings + driver_id);


       //     Toast.makeText(this, "" + incoming_date, Toast.LENGTH_SHORT).show();
            if (incoming_date.equals("NA")) {
                apiManager.execution_method_get("" + Config.ApiKeys.KEY_WEEKLY_EARNING, "" + Apis.weeklyEarnings + driver_id);
            } else {
                apiManager.execution_method_get("" + Config.ApiKeys.KEY_WEEKLY_EARNING, "" + Apis.week_amount + driver_id + "&date=" + incoming_date);
            }

        } catch (Exception e) {
            Log.e("Exception", "" + e);
        }
    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            pd.show();
        }
        if (a == ApiManager.APIFETCHER.KEY_API_IS_STOPPED) {
            pd.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try{ GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if (APINAME.equals("" + Config.ApiKeys.KEY_WEEKLY_EARNING)) {
                try {
                    WeeklyEarningModel weeklyEarningModel;
                    weeklyEarningModel = gson.fromJson("" + script, WeeklyEarningModel.class);

                    if (weeklyEarningModel.getResult() == 1) {
                        // Toast.makeText(this, "" + weeklyEarningModel.getMsg(), Toast.LENGTH_SHORT).show();

                        totalEarningTV.setText(""+sessinManager.getCurrencyCode()+String.valueOf(weeklyEarningModel.getWeekly_amount()));
                        textView4.setText(""+sessinManager.getCurrencyCode() + String.valueOf(weeklyEarningModel.getWeekly_amount()));
                        completedTripsTV.setText(String.valueOf(weeklyEarningModel.getTotal_rides()));
                        fareEarnedTV.setText(""+sessinManager.getCurrencyCode() + String.valueOf(weeklyEarningModel.getFare_recevied()));
                        taxiFeeTV.setText(""+sessinManager.getCurrencyCode() + String.valueOf(weeklyEarningModel.getCompany_cut()));

                        for (int i = 0; i < weeklyEarningModel.getDetails().size(); i++) {

                            try {
                                String start_date = weeklyEarningModel.getDetails().get(0).getDate() + "    to    " + weeklyEarningModel.getDetails().get(weeklyEarningModel.getDetails().size() - 1).getDate();
                                weekDatesTV.setText(start_date);
                                String amount = ""+sessinManager.getCurrencyCode() + weeklyEarningModel.getDetails().get(i).getDetail().getAmount().toString().trim();
                                String week_name = weeklyEarningModel.getDetails().get(i).getDay() + " " + weeklyEarningModel.getDetails().get(i).getDate();
                                incoming_date = weeklyEarningModel.getDetails().get(i).getDate();
                                date1 = weeklyEarningModel.getDetails().get(i).getDate();
                                price_data_list.add(amount);
                                week_data_list.add(week_name);
                                date_list.add(weeklyEarningModel.getDetails().get(i).getDate());
                            } catch (Exception e) {

                            }
                        }
                        for (int i = 0; i < week_data_list.size(); i++) {
                            if (!price_data_list.get(i).equals(sessinManager.getCurrencyCode()+"0")) {
                                mTripContainer.addView(getTripItemView(week_data_list.get(i), price_data_list.get(i), date_list.get(i), this));
                            }
                        }


                    } else {
                    //    Toast.makeText(this, "" + weeklyEarningModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Exception", "" + e);
                }
            }}catch (Exception e ){}
    }

    @Override
    public void onFetchResultZero(String script) {

    }

}
