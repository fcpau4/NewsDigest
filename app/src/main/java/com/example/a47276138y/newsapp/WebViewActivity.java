package com.example.a47276138y.newsapp;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;


//http://www.technotalkative.com/android-webviewclient-example/
public class WebViewActivity extends Activity {


    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        web = (WebView)findViewById(R.id.webView);

    }


}
