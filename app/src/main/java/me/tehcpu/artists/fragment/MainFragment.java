package me.tehcpu.artists.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.json.JSONObject;

import java.util.ArrayList;

import me.tehcpu.artists.R;
import me.tehcpu.artists.RootActivity;
import me.tehcpu.artists.adapter.ArtistsAdapter;
import me.tehcpu.artists.model.Artist;
import me.tehcpu.artists.ui.CustomToolbar;
import me.tehcpu.artists.ui.DividerItemDecoration;
import me.tehcpu.artists.ui.RecyclerItemClickListener;
import me.tehcpu.artists.utils.Parser;

/**
 * Created by codebreak on 19/04/16.
 */
public class MainFragment extends Fragment {

    private static MainFragment instance;
    private String TAG = "MainFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RelativeLayout toolbarLayout = (RelativeLayout) view.findViewById(R.id.toolbarMain);
        CustomToolbar toolbar = new CustomToolbar(view.getContext(), toolbarLayout);

        toolbar.setTitle(getContext().getString(R.string.artists));

        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.artists_view);
        rv.setHasFixedSize(true);
        final ArtistsAdapter adapter = new ArtistsAdapter();
        rv.setAdapter(adapter);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                Parser.getInstance().getData(new Parser.onDataParsed() {
                    @Override
                    public void success(final ArrayList<Artist> data) {
                        RootActivity.getInstance().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                AlphaAnimation animation = new AlphaAnimation(1f, 0f);
                                animation.setDuration(300);
                                animation.setFillAfter(true);
                                animation.setAnimationListener(new Animation.AnimationListener() {
                                    @Override
                                    public void onAnimationStart(Animation animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animation animation) {
                                        progressBar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animation animation) {

                                    }
                                });
                                progressBar.startAnimation(animation);
                                adapter.addAll(data);
                            }
                        });
                    }
                });
                return null;
            }
        }.execute();

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        RootActivity.getInstance().startSingleArtist(adapter.getItem(position));
                    }
                })
        );

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);

        rv.addItemDecoration(new DividerItemDecoration(view.getContext()));


        return view;
    }

    public static MainFragment getInstance() {
        if (instance == null) instance = new MainFragment();
        return instance;
    }

}
