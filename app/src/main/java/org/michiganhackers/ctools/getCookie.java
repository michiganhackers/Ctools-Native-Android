package org.michiganhackers.ctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;


public class getCookie extends ActionBarActivity {
    public static final String TAG = "getCookie";
    WebView webview;
    FrameLayout container;
    String cookies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cookie);

        webview = (WebView) findViewById(R.id.webview);
        container = (FrameLayout) findViewById(R.id.container);

        Callback callback = new Callback();
        webview.setWebViewClient(callback);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.loadUrl("https://weblogin.umich.edu/?cosign-ctools&https://ctools.umich.edu/sakai-login-tool/container");
        onPageFinished(webview, "https://ctools.umich.edu/portal");
       // callback.onPageStarted(webview, "https://ctools.umich.edu/portal", bMap);


       // finish();
    }

    public void closeWebView(View v) {
        container.removeView(webview);

        Intent intent = this.getIntent();
        intent.putExtra("",cookies.contains("cosign-ctools"));
        this.setResult(RESULT_OK);
        finish();
    }

    public void onPageFinished(WebView view, String url) {
/*
        while (view.getUrl() != null && !view.getUrl()("ctool.umich.edu/portal")) {
           Log.d(TAG, view.getUrl());
        }
        */

        Log.d(TAG, "WE MADE IT MAMA");
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        if (webview.getUrl().contains("ctools.umich.edu/portal")) {
            cookies = CookieManager.getInstance().getCookie(webview.getUrl());
            if (cookies != null) {
                Log.d(TAG, "All the cookies in the string: " + cookies);
                String[] cookiesList = cookies.split("cosign=");
                for (String s : cookiesList) {
                    CookieManager.getInstance().setCookie(webview.getUrl(), s);
                    Log.d(TAG, s);
                }
            }
            finish();
            return true;
        } else {
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_cookie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class Callback extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, url);
            return true;
        }
    }
}
