package me.tehcpu.artists.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import me.tehcpu.artists.R;
import me.tehcpu.artists.RootActivity;
import me.tehcpu.artists.model.Artist;
import me.tehcpu.artists.ui.CustomToolbar;

/**
 * Created by codebreak on 19/04/16.
 */
public class SingleArtistFragment extends Fragment {
    private static SingleArtistFragment instance;
    private String childname;
    private String TAG = "SingleArtistFragment";
    private ImageView cover_big;
    private TextView genres;
    private TextView tracks;
    private TextView songs;
    private TextView description;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single, container, false);

        RelativeLayout toolbarLayout = (RelativeLayout) view.findViewById(R.id.toolbarSingle);
        CustomToolbar toolbar = new CustomToolbar(getActivity().getApplicationContext(), toolbarLayout);

        //
        cover_big = (ImageView) view.findViewById(R.id.artist_big_cover);
        genres = (TextView) view.findViewById(R.id.single_genres);
        tracks = (TextView) view.findViewById(R.id.single_tracks);
        songs = (TextView) view.findViewById(R.id.single_songs);
        description = (TextView) view.findViewById(R.id.single_description);
        //

        Log.d(TAG, "initOnView");

        Bundle bundle = getArguments();
        Artist artist = (Artist) bundle.getSerializable("artist");

        toolbar.setTitle(artist.getName());
        toolbar.setBackButton();

        ImageButton backButton = (ImageButton) view.findViewWithTag("toolbarBack");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RootActivity.getInstance().finishSingleArtist();
            }
        });

        instance = this;
        this.view = view;

        //
        Picasso.with(RootActivity.getInstance().getApplicationContext()).load(artist.getCover().getBig()).placeholder(R.drawable.account).into(cover_big);
        genres.setText(artist.getGenres());
        tracks.setText(artist.getAlbums()+" "+artist.getAlbumsHuman());
        songs.setText(artist.getTracks()+" "+artist.getTracksHuman());
        description.setText(artist.getDescription().substring(0,1).toUpperCase() + artist.getDescription().substring(1));
        //

        return view;
    }

    public static SingleArtistFragment getInstance() {
        if (instance == null) instance = new SingleArtistFragment();
        return instance;
    }

    public void updateScreen(Artist artist) {
        Log.d(TAG, "fired!");
        if (view != null) {
            Log.d(TAG, "fired!2");
            cover_big = (ImageView) view.findViewById(R.id.artist_big_cover);
            genres = (TextView) view.findViewById(R.id.single_genres);
            tracks = (TextView) view.findViewById(R.id.single_tracks);
            songs = (TextView) view.findViewById(R.id.single_songs);
            description = (TextView) view.findViewById(R.id.single_description);

            Picasso.with(RootActivity.getInstance().getApplicationContext()).load(artist.getCover().getBig()).placeholder(R.drawable.account).into(cover_big);
            genres.setText(artist.getGenres());
            tracks.setText(artist.getAlbums() + " " + artist.getAlbumsHuman());
            songs.setText(artist.getTracks() + " " + artist.getTracksHuman());
            description.setText(artist.getDescription().substring(0, 1).toUpperCase() + artist.getDescription().substring(1));
        }

    }

}