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

    public void getData(final onDataParsed callback) {

        final JsonArrayRequest req = new JsonArrayRequest("http://cache-default04e.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<Artist> artists = new ArrayList<>();

                for (int i=0; i < response.length(); i++) {
                    try {
                        Artist artist = new Artist();
                        JSONObject item = response.getJSONObject(i);
                        artist.setId(item.getLong("id"));
                        artist.setName(Common.fixEncoding(item.getString("name")));
                        artist.setGenres(item.getJSONArray("genres").toString());
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        ArtistsApplication.getInstance().addToMainRequestQueue(req);

        //
    }

    public interface onDataParsed {
        void success(ArrayList<Artist> data);
        void failed(JSONObject response);
    }

    public static Parser getInstance() {
        if (instance == null) instance = new Parser();
        return instance;
    }
}
