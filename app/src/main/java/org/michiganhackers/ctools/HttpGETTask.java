package org.michiganhackers.ctools;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Async Task to execute a GET request on a given URL. Returns a List of JSONObjects.
 * Created by Omkar Moghe on 3/14/2015.
 */
public class HttpGETTask extends AsyncTask <URL, Integer, List<JSONObject>> {

    public static final String TAG = "HttpGETTask";

    @Override
    protected List<JSONObject> doInBackground(URL... params) {
        List<JSONObject> results = new ArrayList<JSONObject>();

        for (URL url : params) {
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if(isCancelled()) break;

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder data = new StringBuilder();
                String s = "";
                while((s = br.readLine()) != null) data.append(s);

                results.add(new JSONObject(data.toString()));
            } catch (IOException e) {
                Log.e(TAG, "Connection IO error", e);
                e.printStackTrace();
            } catch (JSONException j) {
                Log.e(TAG, "JSON error", j);
            }
        }

        return results;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<JSONObject> results) {
        if (results.size() > 0) Log.d(TAG, results.get(0).toString());
        super.onPostExecute(results);
    }

    // TODO: Implement response
    public interface HttpResponseListener {
        public void httpResponse (List<JSONObject> response);
    }
}
