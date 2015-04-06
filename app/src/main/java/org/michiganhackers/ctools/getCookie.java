package org.michiganhackers.ctools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.CookieHandler;
import java.net.CookieStore;


public class getCookie extends ActionBarActivity {
    public static final String TAG = "getCookie";
    WebView webview = (WebView)findViewById(R.id.webview);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cookie);

        Callback callback = new Callback();
        webview.setWebViewClient(callback);
        webview.getSettings().setJavaScriptEnabled(true);


        webview.loadUrl("https://ctools.umich.edu/portal");
        onPageFinished(webview, "https://ctools.umich.edu/portal");
       // callback.onPageStarted(webview, "https://ctools.umich.edu/portal", bMap);


       // finish();
    }


    public void onPageFinished(WebView view, String url) {
/*
        while (view.getUrl() != null && !view.getUrl().contains("ctools.umich.edu/portal")) {
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
            String cookies = CookieManager.getInstance().getCookie(webview.getUrl());
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
            return false;
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
            return true;
        }
    }
}
