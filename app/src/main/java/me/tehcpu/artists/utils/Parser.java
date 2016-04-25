package me.tehcpu.artists.utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.tehcpu.artists.ArtistsApplication;
import me.tehcpu.artists.model.Artist;
import me.tehcpu.artists.model.ArtistCover;

/**
 * Created by codebreak on 24/04/16.
 */
public class Parser {
    private static Parser instance;
    private int cache = 0;

    public void getData(final onDataParsed callback) {
        String localCache = Cache.get();
        if (localCache != null) {
            JSONArray data = null;
            try {
                data = new JSONArray(localCache);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (localCache.length() > 10) {
                cache = 1;
                prepareArtists(data, callback);
            }
        }
        final JsonArrayRequest req = new JsonArrayRequest("http://cache-default04e.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Cache.save(response.toString());
                if (cache == 0) {
                    prepareArtists(response, callback);
                }
                // fresh data will be available after recycle re-render..
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });

        ArtistsApplication.getInstance().addToMainRequestQueue(req);
    }

    private void prepareArtists(JSONArray data, onDataParsed callback) {
        ArrayList<Artist> artists = new ArrayList<>();

        for (int i=0; i < data.length(); i++) {
            try {
                Artist artist = new Artist();
                JSONObject item = data.getJSONObject(i);
                artist.setId(item.getLong("id"));
                artist.setName(Common.fixEncoding(item.getString("name")));
                // disgustingly solution, but work :D
                artist.setGenres(item.getJSONArray("genres").toString().replace("[", "").replace("]", "").replace("\"", "").replace(",", ", "));
                artist.setTracks(item.getLong("tracks"));
                artist.setAlbums(item.getLong("albums"));
                artist.setLink(item.getString("link"));
                artist.setDescription(Common.fixEncoding(item.getString("description")));

                JSONObject cover = item.getJSONObject("cover");
                artist.setCover(new ArtistCover(cover.getString("small"), cover.getString("big")));

                artists.add(artist);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        callback.success(artists);
    }

    public interface onDataParsed {
        void success(ArrayList<Artist> data);
    }

    public static Parser getInstance() {
        if (instance == null) instance = new Parser();
        return instance;
    }
}
