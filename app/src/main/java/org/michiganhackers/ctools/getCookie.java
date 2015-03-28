package org.michiganhackers.ctools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.CookieHandler;
import java.net.CookieStore;


public class getCookie extends ActionBarActivity {
    public static final String TAG = "getCookie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_cookie);

        WebView webview = (WebView)findViewById(R.id.webview);
        Callback callback = new Callback();
        webview.setWebViewClient(callback);
        //webview.getSettings().setJavaScriptEnabled(true);
        Bitmap bMap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

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


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String cookies = CookieManager.getInstance().getCookie(url);
            if (cookies != null) {
                Log.d(TAG, "All the cookies in the string: " + cookies);
                String[] cookiesList = cookies.split("cosign=");
                for (String s: cookiesList) {
                    CookieManager.getInstance().setCookie(url, s);
                    Log.d(TAG, s);
                }
            }
        }
    }
}
