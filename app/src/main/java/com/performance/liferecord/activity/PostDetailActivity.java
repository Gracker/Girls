package com.performance.liferecord.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.performance.liferecord.R;
import com.performance.liferecord.model.GankData;

public class PostDetailActivity extends AppCompatActivity {

    private WebView mWebView;
    private String postUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        setContentView(R.layout.activity_post_detail);
        getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        mWebView = (WebView) findViewById(R.id.web_view);
        final Activity MyActivity = this;
        WebSettings webSettings = null;
        if (mWebView != null) {
            webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
            webSettings.setSupportZoom(true);
            webSettings.setSupportMultipleWindows(true);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    MyActivity.setTitle(getString(R.string.loading));
                    MyActivity.setProgress(newProgress * 100);
                    // Return the app name after finish loading
                    if (newProgress == 100)
                        MyActivity.setTitle(R.string.app_name);
                }

            });
        }

        getData();

    }

    private void getData() {
        Intent intent = getIntent();
        postUrl = intent.getStringExtra(GankData.POST_URL);
        mWebView.loadUrl(postUrl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return;
        }
        super.onBackPressed();
    }
}
