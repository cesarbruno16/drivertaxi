package com.ride.taxiDriver;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apporio.apporiologs.ApporioLog;
import com.ride.taxiDriver.manager.SessionManager;
import com.ride.taxiDriver.models.restmodels.NewRidehistoryModel;
import com.ride.taxiDriver.models.restmodels.ResultStatusChecker;
import com.ride.taxiDriver.samwork.ApiManager;
import com.ride.taxiDriver.trackride.TrackRideActivity;
import com.ride.taxiDriver.typeface.TypefaceTextView;
import com.ride.taxiDriver.urls.Apis;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.sam.placer.PlaceHolderView;
import com.sam.placer.annotations.Click;
import com.sam.placer.annotations.Layout;
import com.sam.placer.annotations.Resolve;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TripHistoryActivity extends BaseActivity implements ApiManager.APIFETCHER {


    private static final String TAG = "TripHistoryActivity";
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.root_action_bar)
    LinearLayout rootActionBar;
    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.container)
    ViewPager container;
    @Bind(R.id.activity_name)
    TypefaceTextView activityName;
    @Bind(R.id.refresh)
    ImageView refresh;
    @Bind(R.id.root)
    LinearLayout root;
    private PagerAdapter mSectionsPagerAdapter;
    ApiManager apiManager;
    SessionManager sessionManager;
    public static Activity activity;
    private static NewRidehistoryModel mRideshistory_response;
    ProgressDialog progressDialog;
    private int OPEN_TAB = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_history);
        ButterKnife.bind(this);
        apiManager = new ApiManager(this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(this.getResources().getString(R.string.loading));
        activity = this;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        activityName.setText(this.getResources().getString(R.string.your_trips));

        try{OPEN_TAB = Integer.parseInt(""+getIntent().getExtras().getString("tab_number"));}catch (Exception E){}


        try {
            RentalTrackRideActivity.activity.finish();
        } catch (Exception e) {
        }
        try {
            TrackRideActivity.activity.finish();
        } catch (Exception e) {
        }


        mRideshistory_response = null ;
        mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);
        viewpagertab.setViewPager(container);

        container.setCurrentItem(OPEN_TAB);


        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> data = new HashMap<>();
                data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
                apiManager.execution_method_post("" + Config.ApiKeys.KEY_REST_RIDE_HISTORY, "" + Apis.RideHistory, data);
            }
        });
        refresh.performClick();
    }


    @Override
    protected void onResume() {
        super.onResume();
        HashMap<String, String> data = new HashMap<>();
        data.put("driver_id", "" + sessionManager.getUserDetails().get(SessionManager.KEY_DRIVER_ID));
        apiManager.execution_method_post("" + Config.ApiKeys.KEY_REST_RIDE_HISTORY, "" + Apis.RideHistory, data);
    }

    @Override
    protected void onResumeWithConnectionState(boolean connectivityStatus) {

    }

    @Override
    public void onAPIRunningState(int a, String APINAME) {

        if (a == ApiManager.APIFETCHER.KEY_API_IS_STARTED) {
            progressDialog.show();
        } else if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void onFetchComplete(Object script, String APINAME) {
        try {
            Gson gson = new GsonBuilder().create();
            ResultStatusChecker rs = gson.fromJson("" + script, ResultStatusChecker.class);
            if(rs.getStatus() == 1){
                mRideshistory_response = gson.fromJson("" + script, NewRidehistoryModel.class);
                mSectionsPagerAdapter = new PagerAdapter(getSupportFragmentManager());
                container.setAdapter(mSectionsPagerAdapter);
                viewpagertab.setViewPager(container);
                container.setCurrentItem(OPEN_TAB);
            }



        } catch (Exception e) {
            ApporioLog.logE("" + TAG, "Exception Caught while parsing ==>" + e.getMessage());
        }
    }

    @Override
    public void onFetchResultZero(String script) {
    }





    public static class ActiveRideFragment extends Fragment {

        @Bind(R.id.place_holder)
        PlaceHolderView placeHolder;

        public ActiveRideFragment() {
        }

        public static ActiveRideFragment newInstance(int sectionNumber) {
            ActiveRideFragment fragment = new ActiveRideFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.active_ride_fragment, container, false);
            ButterKnife.bind(this, rootView);


            if(mRideshistory_response == null){
            } else{
                try{for(int i = 0 ;i < placeHolder.getChildCount() ; i++){placeHolder.removeView(i);}}catch (Exception e){Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();}
                try{for (int i = 0; i < mRideshistory_response.getDetails().size(); i++) {
                    if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("1")) {   // ride type normal
                        if (mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_ACCEPTED) ||
                                mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_ARRIVED) ||
                                mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_STARTED) ||
                                mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.PARTIAL_ACCEPTED)) {
                            placeHolder.addView(new HolderRideHistoryNormal(getActivity(), mRideshistory_response.getDetails().get(i).getNormal_Ride()));
                        }
                    } if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("2")) {   // ride type Rentals
                        if (mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_ACCEPTED) ||
                                mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_ARRIVED) ||
                                mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAl_RIDE_STARTED) ||
                                mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals(Config.Status.PARTIAL_ACCEPTED)) {
                            placeHolder.addView(new HolderRentalRideHistory(getActivity(), mRideshistory_response.getDetails().get(i).getRental_Ride()));

                        }
                    }
                }


                }catch (Exception e){}
            }
            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }

    public static class NewRequestFragment extends Fragment {

        @Bind(R.id.place_holder)
        PlaceHolderView placeHolder;

        public NewRequestFragment() {
        }

        public static NewRequestFragment newInstance() {
            NewRequestFragment fragment = new NewRequestFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.new_request_fragments, container, false);
            ButterKnife.bind(this, rootView);



            try{
                if(mRideshistory_response == null ){
                }else{
                    try{for(int i = 0 ;i < placeHolder.getChildCount() ; i++){placeHolder.removeView(i);}}catch (Exception e){}
                    for (int i = 0; i < mRideshistory_response.getDetails().size(); i++) {

                        if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("1")) {   // ride type normal
                            if (mRideshistory_response.getDetails().get(i).getRide_type().equals("2") && !mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals("22")
                                    && !mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_CANCEL_BY_USER)
                                    && !mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_RIDE_CANCEl_BY_ADMIN)
                                    && !mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_CANCEL_BY_DRIVER)
                                    && !mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals(Config.Status.NORMAL_RIDE_END)) {
                                placeHolder.addView(new HolderNewRequestNormal(getActivity(), mRideshistory_response.getDetails().get(i).getNormal_Ride()));
                            }
                        } if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("2")) {   // ride type Rentals
                            if (mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_type().equals("2")
                                    && !mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("22")
                                    && !mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals(Config.Status.RENTAL_RIDE_CANCEL_BY_USER)
                                    && !mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals(Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN)
                                    && !mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals(Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER)
                                    && !mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals(Config.Status.RENTAL_RIDE_END)) {
                                placeHolder.addView(new HoldernewRequesRental(getActivity(), mRideshistory_response.getDetails().get(i).getRental_Ride()));
                            }
                        }
                    }
                }




            }catch (Exception e){}


            return rootView;
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }

    public static class HistoryFragment extends Fragment {
        @Bind(R.id.place_holder)
        PlaceHolderView placeHolder;

        public HistoryFragment() {
        }

        public static HistoryFragment newInstance(int sectionNumber) {
            HistoryFragment fragment = new HistoryFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.history_fragment, container, false);
            ButterKnife.bind(this, rootView);

            try{
                if(mRideshistory_response == null){
                    /// need to handle this
                }else{
                    try{for(int i = 0 ;i < placeHolder.getChildCount() ; i++){placeHolder.removeView(i);}}catch (Exception e){}
                    for (int i = 0; i < mRideshistory_response.getDetails().size(); i++) {
                        if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("1")) {   // ride type normal
                            if (mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals("" + Config.Status.NORMAL_CANCEL_BY_USER) ||
                                    mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals("" + Config.Status.NORMAL_CANCEL_BY_DRIVER) ||
                                    mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals("" + Config.Status.NORMAL_RIDE_END) ||
                                    mRideshistory_response.getDetails().get(i).getNormal_Ride().getRide_status().equals("" + Config.Status.NORMAL_RIDE_CANCEl_BY_ADMIN)) {
                                placeHolder.addView(new HolderRideHistoryNormal(getActivity(), mRideshistory_response.getDetails().get(i).getNormal_Ride()));
                            }
                        } if (mRideshistory_response.getDetails().get(i).getRide_mode().equals("2")) {   // ride type Rentals
                            if (mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_RIDE_CANCELLED_BY_DRIVER) ||
                                    mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_RIDE_CANCEL_BY_USER) ||
                                    mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_RIDE_CANCEl_BY_ADMIN) ||
                                    mRideshistory_response.getDetails().get(i).getRental_Ride().getBooking_status().equals("" + Config.Status.RENTAL_RIDE_END)) {
                                placeHolder.addView(new HolderRentalRideHistory(getActivity(), mRideshistory_response.getDetails().get(i).getRental_Ride()));
                            }
                        }
                    }
                }
            }catch (Exception e){}
            return rootView;
        }


        @Override
        public void onDestroyView() {
            super.onDestroyView();
            ButterKnife.unbind(this);
        }
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {
        String[] fragmens_name = {getString(R.string.on__going), getString(R.string.upcomings), getString(R.string.past_trips)};

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return ActiveRideFragment.newInstance(position);
            }
            if (position == 1) {
                return NewRequestFragment.newInstance();
            } else {
                return HistoryFragment.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            return fragmens_name.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmens_name[position];
        }
    }

















    @Layout(R.layout.rides_item_new)
    public static class HolderRideHistoryNormal {
        @com.sam.placer.annotations.View(R.id.tv_date_time)
        private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss)
        private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss)
        private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt)
        private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt)
        private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status)
        private TextView tv_status;

        NewRidehistoryModel.DetailsBean.NormalRideBean mNormalRideResponse;

        public HolderRideHistoryNormal(Context context, NewRidehistoryModel.DetailsBean.NormalRideBean normalRideResponse) {
            mNormalRideResponse = normalRideResponse;
        }

        @Resolve
        private void onResolved() {
            tv_date_time.setText("#"+mNormalRideResponse.getRide_id()+"  " + mNormalRideResponse.getRide_date());
            tv_pickup_addresss.setText("" + mNormalRideResponse.getPickup_location());
            tv_drop_addresss.setText("" + mNormalRideResponse.getDrop_location());
            customer_name_txt.setText("" + mNormalRideResponse.getUser_name());
            customer_phone_txt.setText("" + mNormalRideResponse.getUser_phone());
            tv_status.setText("" + Config.getStatustext("" + mNormalRideResponse.getRide_status()));
        }

        @Click(R.id.root)
        private void onclick() {
            String date_time = mNormalRideResponse.getRide_date() + ", " + mNormalRideResponse.getRide_time();
            if (mNormalRideResponse.getRide_status().equals("" + Config.Status.PARTIAL_ACCEPTED)) {
                activity.startActivity(new Intent(activity.getBaseContext(), AcceptPassActivity.class)
                        .putExtra("ride_id", "" + mNormalRideResponse.getRide_id())
                        .putExtra("ride_status", "" + mNormalRideResponse.getRide_status())
                        .putExtra("ride_type", "1")
                        .putExtra("later_date", mNormalRideResponse.getLater_date())
                        .putExtra("later_time", mNormalRideResponse.getLater_time())
                        .putExtra("booking_date", mNormalRideResponse.getRide_date())
                        .putExtra("booking_time", mNormalRideResponse.getRide_time())
                        .putExtra("user_name", mNormalRideResponse.getUser_name())
                        .putExtra("user_phone", mNormalRideResponse.getUser_phone())
                        .putExtra("user_rating", mNormalRideResponse.getUser_rating())
                        .putExtra("user_image", "" + mNormalRideResponse.getUser_image())
                        .putExtra("pick_up_location", "" + mNormalRideResponse.getPickup_location())
                        .putExtra("drop_off_location", "" + mNormalRideResponse.getDrop_location()));
            } else {
                activity.startActivity(new Intent(activity.getBaseContext(), SelectedRidesActivity.class).putExtra("ride_id", "" + mNormalRideResponse.getRide_id())
                        .putExtra("ride_status", "" + mNormalRideResponse.getRide_status())
                        .putExtra("date_time", date_time)
                        .putExtra("ride_type", "" + mNormalRideResponse.getRide_type())  // that is later or ride now type
                        .putExtra("ride_mode", "1")
                        .putExtra("ride_id", "" + mNormalRideResponse.getRide_id()));
            }
        }
    }


    @Layout(R.layout.ride_history_rental_item)
    public static class HolderRentalRideHistory {

        @com.sam.placer.annotations.View(R.id.tv_date_time)
        private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss)
        private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss)
        private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt)
        private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt)
        private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status)
        private TextView tv_status;
        @com.sam.placer.annotations.View(R.id.drop_locatipon_layout)
        private LinearLayout drop_locatipon_layout;


        NewRidehistoryModel.DetailsBean.RentalRideBean mRentalRideResponse;


        private Context mContext;

        public HolderRentalRideHistory(Context context, NewRidehistoryModel.DetailsBean.RentalRideBean rental_ride) {
            mContext = context;
            mRentalRideResponse = rental_ride;
        }

        @Resolve
        private void onResolved() {
            tv_date_time.setText("#"+mRentalRideResponse.getRental_booking_id() +"  "+ mRentalRideResponse.getBooking_date());
            tv_pickup_addresss.setText("" + mRentalRideResponse.getPickup_location());
            tv_drop_addresss.setText("" + mRentalRideResponse.getEnd_location());
            customer_name_txt.setText("" + mRentalRideResponse.getUser_name());
            customer_phone_txt.setText("" + mRentalRideResponse.getUser_phone());
            tv_status.setText("" + Config.getStatustext("" + mRentalRideResponse.getBooking_status()));

            if (mRentalRideResponse.getEnd_location().equals("")) {
                drop_locatipon_layout.setVisibility(View.GONE);
            }
        }


        @Click(R.id.root)
        private void onclick() {
            String date_time = mRentalRideResponse.getBooking_date() + ", " + mRentalRideResponse.getBooking_time();


            if (mRentalRideResponse.getBooking_status().equals("" + Config.Status.PARTIAL_ACCEPTED)) {
                activity.startActivity(new Intent(activity.getBaseContext(), AcceptPassActivity.class)
                        .putExtra("ride_id", "" + mRentalRideResponse.getRental_booking_id())
                        .putExtra("ride_status", "" + mRentalRideResponse.getBooking_status())
                        .putExtra("ride_type", "2")
                        .putExtra("later_date", mRentalRideResponse.getBooking_date())
                        .putExtra("later_time", mRentalRideResponse.getBooking_time())
                        .putExtra("booking_date", mRentalRideResponse.getUser_booking_date_time())
                        .putExtra("booking_time", "")
                        .putExtra("user_name", mRentalRideResponse.getUser_name())
                        .putExtra("user_phone", mRentalRideResponse.getUser_phone())
                        .putExtra("user_rating", mRentalRideResponse.getRating())
                        .putExtra("user_image", "" + mRentalRideResponse.getUser_image())
                        .putExtra("pick_up_location", "" + mRentalRideResponse.getPickup_location())
                        .putExtra("drop_off_location", "" + mRentalRideResponse.getEnd_location()));
            } else {
                activity.startActivity(new Intent(activity.getBaseContext(), SelectedRentalRideActivity.class)
                        .putExtra("ride_id", "" + mRentalRideResponse.getRental_booking_id())
                        .putExtra("ride_status", "" + mRentalRideResponse.getBooking_status())
                        .putExtra("date_time", date_time)
                        .putExtra("ride_mode", "2"));
            }
        }

    }


    @Layout(R.layout.item_new_request_normal)
    public static class HolderNewRequestNormal {
        @com.sam.placer.annotations.View(R.id.tv_date_time)
        private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss)
        private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss)
        private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt)
        private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt)
        private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status)
        private TextView tv_status;

        NewRidehistoryModel.DetailsBean.NormalRideBean mNormalRideResponse;

        public HolderNewRequestNormal(Context context, NewRidehistoryModel.DetailsBean.NormalRideBean normalRideResponse) {
            mNormalRideResponse = normalRideResponse;
        }

        @Resolve
        private void onResolved() {
            tv_date_time.setText("#"+mNormalRideResponse.getRide_id() +"  "+ mNormalRideResponse.getLater_date() + " | " + mNormalRideResponse.getLater_time());
            tv_pickup_addresss.setText("" + mNormalRideResponse.getPickup_location());
            tv_drop_addresss.setText("" + mNormalRideResponse.getDrop_location());
            customer_name_txt.setText("" + mNormalRideResponse.getUser_name());
            customer_phone_txt.setText("" + mNormalRideResponse.getUser_phone());
            tv_status.setText(R.string.accept_pass);
        }

        @Click(R.id.root)
        private void onclick() {
            activity.startActivity(new Intent(activity.getBaseContext(), AcceptPassActivity.class)
                    .putExtra("ride_id", "" + mNormalRideResponse.getRide_id())
                    .putExtra("ride_status", "" + mNormalRideResponse.getRide_status())
                    .putExtra("ride_type", "1")
                    .putExtra("later_date", mNormalRideResponse.getLater_date())
                    .putExtra("later_time", mNormalRideResponse.getLater_time())
                    .putExtra("booking_date", mNormalRideResponse.getRide_date())
                    .putExtra("booking_time", mNormalRideResponse.getRide_time())
                    .putExtra("user_name", mNormalRideResponse.getUser_name())
                    .putExtra("user_phone", mNormalRideResponse.getUser_phone())
                    .putExtra("user_rating", mNormalRideResponse.getUser_rating())
                    .putExtra("user_image", "" + mNormalRideResponse.getUser_image())
                    .putExtra("pick_up_location", "" + mNormalRideResponse.getPickup_location())
                    .putExtra("drop_off_location", "" + mNormalRideResponse.getDrop_location()));
        }
    }


    @Layout(R.layout.item_new_request_rental)
    public static class HoldernewRequesRental {

        @com.sam.placer.annotations.View(R.id.tv_date_time)
        private TextView tv_date_time;
        @com.sam.placer.annotations.View(R.id.tv_pickup_addresss)
        private TextView tv_pickup_addresss;
        @com.sam.placer.annotations.View(R.id.tv_drop_addresss)
        private TextView tv_drop_addresss;
        @com.sam.placer.annotations.View(R.id.customer_name_txt)
        private TextView customer_name_txt;
        @com.sam.placer.annotations.View(R.id.customer_phone_txt)
        private TextView customer_phone_txt;
        @com.sam.placer.annotations.View(R.id.tv_status)
        private TextView tv_status;
        @com.sam.placer.annotations.View(R.id.drop_locatipon_layout)
        private LinearLayout drop_locatipon_layout;


        NewRidehistoryModel.DetailsBean.RentalRideBean mRentalRideResponse;


        private Context mContext;

        public HoldernewRequesRental(Context context, NewRidehistoryModel.DetailsBean.RentalRideBean rental_ride) {
            mContext = context;
            mRentalRideResponse = rental_ride;
        }

        @Resolve
        private void onResolved() {
            tv_date_time.setText("#"+ mRentalRideResponse.getRental_booking_id()+"  " + mRentalRideResponse.getBooking_date());
            tv_pickup_addresss.setText("" + mRentalRideResponse.getPickup_location());
            tv_drop_addresss.setText("" + mRentalRideResponse.getEnd_location());
            customer_name_txt.setText("" + mRentalRideResponse.getUser_name());
            customer_phone_txt.setText("" + mRentalRideResponse.getUser_phone());
            tv_status.setText("" + Config.getStatustext("" + mRentalRideResponse.getBooking_status()));

            if (mRentalRideResponse.getEnd_location().equals("")) {
                drop_locatipon_layout.setVisibility(View.GONE);
            }
        }


        @Click(R.id.root)
        private void onclick() {
            activity.startActivity(new Intent(activity.getBaseContext(), AcceptPassActivity.class)
                    .putExtra("ride_id", "" + mRentalRideResponse.getRental_booking_id())
                    .putExtra("ride_status", "" + mRentalRideResponse.getBooking_status())
                    .putExtra("ride_type", "2")
                    .putExtra("later_date", mRentalRideResponse.getBooking_date())
                    .putExtra("later_time", mRentalRideResponse.getBooking_time())
                    .putExtra("booking_date", mRentalRideResponse.getUser_booking_date_time())
                    .putExtra("booking_time", "")
                    .putExtra("user_name", mRentalRideResponse.getUser_name())
                    .putExtra("user_phone", mRentalRideResponse.getUser_phone())
                    .putExtra("user_rating", mRentalRideResponse.getRating())
                    .putExtra("user_image", "" + mRentalRideResponse.getUser_image())
                    .putExtra("pick_up_location", "" + mRentalRideResponse.getPickup_location())
                    .putExtra("drop_off_location", "" + mRentalRideResponse.getEnd_location()));
        }

    }


    @Layout(R.layout.no_rides_holder)
    public static class HolderNorRides{

    }


}
