package com.ride.taxiDriver.earnings;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ride.taxiDriver.Config;
import com.ride.taxiDriver.R;
import com.ride.taxiDriver.SelectedRentalRideActivity;
import com.ride.taxiDriver.SelectedRidesActivity;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewDriverReportModel;
import com.ride.taxiDriver.newmodels.DailyEarningModel;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyStatementActivity extends Activity implements ApiManager.APIFETCHER {

    @Bind(R.id.trip_container)
    LinearLayout mContainer;
    LinearLayout backBtnLL;
    ApiManager apiManager;
    SessionManager sessinManager;
    String driver_id, date, head_date;
    ProgressDialog pd;
    TextView totalEarningTV, totalRidesTV, totalEarningTV1;
    ArrayList<String> cnrArrayList;
    ArrayList<String> dateArrayList;
    ArrayList<String> amountArrayList;
    ArrayList<String> ride_mode;
    TextView headDateTV, faredEarnedTV, companyTaxTV , online_time  ,overall_rating , acceptance_rate ;

    DailyEarningModel dailyEarningModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_statement);
        ButterKnife.bind(this);

        apiManager = new ApiManager(this);
        sessinManager = new SessionManager(DailyStatementActivity.this);
        pd = new ProgressDialog(this);
        pd.setMessage("" + this.getResources().getString(R.string.loading));
        driver_id = new SessionManager(DailyStatementActivity.this).getUserDetails().get(SessionManager.KEY_DRIVER_ID);
        date = super.getIntent().getExtras().getString("Date");
        head_date = super.getIntent().getExtras().getString("Head_date");

        Log.e("Head_date", "" + date);

        cnrArrayList = new ArrayList<>();
        ride_mode = new ArrayList<>();
        dateArrayList = new ArrayList<>();
        amountArrayList = new ArrayList<>();

        backBtnLL = (LinearLayout) findViewById(R.id.backBtnLL);
        totalEarningTV = (TextView) findViewById(R.id.totalEarningTV);
        totalRidesTV = (TextView) findViewById(R.id.totalRidesTV);
        totalEarningTV1 = (TextView) findViewById(R.id.totalEarningTV1);
        headDateTV = (TextView) findViewById(R.id.headDateTV);
        faredEarnedTV = (TextView) findViewById(R.id.faredEarnedTV);
        companyTaxTV = (TextView) findViewById(R.id.companyTaxTV);

        online_time = (TextView) findViewById(R.id.online_time);
        overall_rating = (TextView) findViewById(R.id.overall_rating);
        acceptance_rate = (TextView) findViewById(R.id.acceptance_rate);



        headDateTV.setText(head_date);

        backBtnLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DailyStatementActivity.this.finish();
            }
        });

        weekelyEarningApi();

//        for (int i = 0; i < ride_data.length; i++) {
//            mContainer.addView(getTripItemView(ride_data[i], amount_data[i], ride_date[i]));
//        }
    }

    private void weekelyEarningApi() {

        try {
            Log.e("Name", "" + Config.ApiKeys.KEY_DAILY_EARNING);
            Log.e("ApiName", "" + Config.ApiKeys.KEY_DAILY_EARNING + "" + Apis.dailyEarnings + driver_id + "&" + "date=" + date);

            apiManager.execution_method_get("" + Config.ApiKeys.KEY_DAILY_EARNING, "" + Apis.dailyEarnings + driver_id + "&" + "date=" + date);
        } catch (Exception e) {
            Log.e("Exception", "" + e);
        }


        HashMap<String , String > data  = new HashMap<>();
        data.put("driver_id" , ""+sessinManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        data.put("driver_token" , ""+sessinManager.getUserDetails().get(SessionManager.KEY_DriverToken));
        apiManager.execution_method_post("driver_report" , ""+ Apis.DriverReport, data );
    }


    public View getTripItemView(final String ride_id, String date, String amount , final String ride_mode) {
        View child = getLayoutInflater().inflate(R.layout.trip_item, null);
        TextView trip_no_txt = (TextView) child.findViewById(R.id.trip_no_txt);
        TextView amount_txt = (TextView) child.findViewById(R.id.amount_txt);
        TextView date_txt = (TextView) child.findViewById(R.id.date_txt);


        trip_no_txt.setText("CRN" + " " + "" + ride_id);
        amount_txt.setText(""+sessinManager.getCurrencyCode() + amount.toString());
        date_txt.setText("" + date);


        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ride_mode.equals("1")){
                    startActivity(new Intent(DailyStatementActivity.this, SelectedRidesActivity.class)
                            .putExtra("ride_id", ride_id)
                            .putExtra("ride_status", "7"));
                }else if (ride_mode.equals("2")){

                    startActivity(new Intent(DailyStatementActivity.this, SelectedRentalRideActivity.class)
                            .putExtra("ride_id", ride_id)
                            .putExtra("ride_status", "7")
                            .putExtra("ride_mode" , "2"));

                }else{
                    Toast.makeText(DailyStatementActivity.this, "Ride Mode is not proper ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return child;

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

        try{GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            if (APINAME.equals("" + Config.ApiKeys.KEY_DAILY_EARNING)) {
                try {

                    dailyEarningModel = gson.fromJson("" + script, DailyEarningModel.class);

                    if (dailyEarningModel.getResult() == 1) {

                        try {
                            totalEarningTV.setText(""+sessinManager.getCurrencyCode() + dailyEarningModel.getDetails().getAmount().toString());
                            totalEarningTV1.setText(""+sessinManager.getCurrencyCode() + dailyEarningModel.getDetails().getAmount().toString());
                            totalRidesTV.setText(dailyEarningModel.getDetails().getRides().toString());
                            faredEarnedTV.setText(""+sessinManager.getCurrencyCode()+String.valueOf(dailyEarningModel.getDetails().getFare_recevied()));
                            companyTaxTV.setText(""+sessinManager.getCurrencyCode()+String.valueOf(dailyEarningModel.getDetails().getCompany_cut()));

                            for (int i = 0; i < dailyEarningModel.getDetails().getFull_ride_details().size(); i++) {

                                cnrArrayList.add(dailyEarningModel.getDetails().getFull_ride_details().get(i).getRide_id().toString());
                                dateArrayList.add(dailyEarningModel.getDetails().getFull_ride_details().get(i).getRide_time().toString());
                                amountArrayList.add(dailyEarningModel.getDetails().getFull_ride_details().get(i).getAmount().toString());
                                ride_mode.add(""+dailyEarningModel.getDetails().getFull_ride_details().get(i).getRide_mode());
                            }
                            for (int i = 0; i < cnrArrayList.size(); i++) {
                                mContainer.addView(getTripItemView(cnrArrayList.get(i), dateArrayList.get(i), amountArrayList.get(i) , ride_mode.get(i)));
                            }

                        } catch (Exception e) {
                            Log.e("Exception", "" + e);
                        }

                    } else {
                        Toast.makeText(this, "" + dailyEarningModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("Exception", "" + e);
                }
            }
            if(APINAME.equals("driver_report")){
                NewDriverReportModel report_respones = gson.fromJson(""+script , NewDriverReportModel.class);

                acceptance_rate.setText(""+report_respones.getDetails().getDaily_acceptance_rate());
                online_time.setText(""+report_respones.getDetails().getOnline_time());
                overall_rating.setText(""+report_respones.getDetails().getAvrage_rating());

            }}catch (Exception e){}

    }

    @Override
    public void onFetchResultZero(String script) {

    }

}
