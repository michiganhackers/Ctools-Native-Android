package org.michiganhackers.ctools;

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
        webview.setWebViewClient(new Callback());
        webview.loadUrl("https://weblogin.umich.edu/?cosign-ctools&https://ctools.umich.edu/sakai-login-tool/container");
        if (webview.getUrl() != null) {
            onPageFinished(webview, "https://weblogin.umich.edu/?cosign-ctools&https://ctools.umich.edu/sakai-login-tool/container");
        }

        Log.d(TAG, webview.getUrl());

        while (webview.getUrl() != null && webview.getUrl().contains("ctools.umich.edu/portal")) {
        }
        //finish();
    }


    public void onPageFinished(WebView view, String url){
        String cookies = CookieManager.getInstance().getCookie(url);
        if (cookies != null) {
            Log.d(TAG, "All the cookies in a string: " + cookies);
            String[] cookiesList = cookies.split("cosign=");
            String loginCookie = cookiesList[0];
            CookieManager.getInstance().setCookie(url, loginCookie);
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
