package com.ride.taxiDriver;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NotificationWebViewActivity extends Activity {

    @Bind(R.id.back)
    LinearLayout back;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.progressBar)
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_notification_web_view);
        ButterKnife.bind(this);

        webview.setWebChromeClient(new MyWebViewClient());
        progress.setMax(100);
        webview.getSettings().setJavaScriptEnabled(true);
        Log.d("*****weburl", ""+getIntent().getExtras().getString("web_url"));
        try{webview.loadUrl(""+getIntent().getExtras().getString("web_url"));}catch (Exception e){}
        NotificationWebViewActivity.this.progress.setProgress(0);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {finish();}});
    }
    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            NotificationWebViewActivity.this.setValue(newProgress);
            super.onProgressChanged(view, newProgress);
        }
    }


    public void setValue(int progress) {
        this.progress.setProgress(progress);
    }

}
