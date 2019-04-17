package com.ride.taxiDriver.others;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ride.taxiDriver.BaseActivity;
import com.ride.taxiDriver.Config;
import com.ride.taxiDriver.R;

public class NoInterNetDialogFragment extends DialogFragment  {

    LinearLayout root ;

    public interface InternetDialogListener {
        void onInternetDialogFinish();
    }

    // Empty constructor required for DialogFragment
    public NoInterNetDialogFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_internet_connectivity, container);
        root = (LinearLayout) view.findViewById(R.id.root);

        getDialog().setTitle("----");
        getDialog().setCancelable(false);


        root.setMinimumWidth(Config.getScreenWidth(getActivity())-150);
        root.setMinimumHeight(Config.getScreenheight(getActivity())-150);

        view.findViewById(R.id.try_again_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BaseActivity.Connectivity.isConnected(getActivity())){
                    dismiss();
                }else {
                    Toast.makeText(getActivity(), R.string.NO_INTERNET_DIALOG_FRAGMENT__please_check_your_internet_connectivity, Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.internet_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        InternetDialogListener activity = (InternetDialogListener)getActivity();
        activity.onInternetDialogFinish();
    }
}