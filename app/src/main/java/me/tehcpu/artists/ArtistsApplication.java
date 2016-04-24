package me.tehcpu.artists;

import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.CacheRequest;

/**
 * Created by codebreak on 19/04/16.
 */
public class ArtistsApplication extends Application {

    private static ArtistsApplication instance;
    private RequestQueue mainRequestQueue;

    public ArtistsApplication(){
        super();
        instance = this;
    }

    public static ArtistsApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RequestQueue getRequestQueue() {
        if (mainRequestQueue == null) {
            mainRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mainRequestQueue;
    }

    // Additions to queues

    public <T> void addToMainRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
        req.setTag("Main");
        Log.d("Volley | Main", req.toString());
    }

    // Cancelers for requests

    public void cancelMainPendingRequests() {
        if (mainRequestQueue != null) {
            mainRequestQueue.cancelAll("Main");
        }
    }
}