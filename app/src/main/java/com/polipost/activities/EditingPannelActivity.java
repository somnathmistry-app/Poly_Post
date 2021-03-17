package com.polipost.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.polipost.R;

public class EditingPannelActivity extends AppCompatActivity {
    String baseUrl = "https://photoedit.aictsolution.com/";
    private WebView webView;
    WebSettings webSettings ;

   // @SuppressLint("SetJavaScriptEnabled")
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_pannel);
        webView = findViewById(R.id.webview_id);
        webSettings = webView.getSettings();


        webView.clearCache(true);
        webView.requestFocus();

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        webSettings.setJavaScriptEnabled(true);

        webSettings.getJavaScriptEnabled();

        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);

        webView.loadUrl(baseUrl);

    }


//    @SuppressLint("SetJavaScriptEnabled")
//    public void getUrl(String myUrl) {
//        isInternetConnection();
//        webView.loadUrl(myUrl);
//
//        webView.clearCache(true);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient() {
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                webView.loadUrl("file:///android_asset/e.html");
//                //relativeLayout.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//
//                return false;
//            }
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                //progressBar.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                //progressBar.setVisibility(View.GONE);
//            }
//        });
//    }

//    @SuppressLint("RestrictedApi")
//    public boolean isInternetConnection() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//            //we are connected to a network
//           // relativeLayout.setVisibility(View.INVISIBLE);
//            webView.setVisibility(View.VISIBLE);
//            //myHomeToolBar.setVisibility(View.VISIBLE);
//            return true;
//        } else {
//
//            webView.setVisibility(View.INVISIBLE);
//            //myHomeToolBar.setVisibility(View.GONE);
//            try {
//                //relativeLayout.setVisibility(View.VISIBLE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
//
//    }


}