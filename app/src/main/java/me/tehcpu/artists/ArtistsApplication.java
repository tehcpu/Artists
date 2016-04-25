package me.tehcpu.artists;

import android.app.Application;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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