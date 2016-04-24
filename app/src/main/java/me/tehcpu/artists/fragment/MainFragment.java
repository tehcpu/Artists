package me.tehcpu.artists.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import me.tehcpu.artists.ArtistsApplication;
import me.tehcpu.artists.R;
import me.tehcpu.artists.RootActivity;
import me.tehcpu.artists.adapter.ArtistsAdapter;
import me.tehcpu.artists.model.Artist;
import me.tehcpu.artists.model.ArtistCover;
import me.tehcpu.artists.ui.CustomToolbar;
import me.tehcpu.artists.ui.DividerItemDecoration;
import me.tehcpu.artists.ui.RecyclerItemClickListener;
import me.tehcpu.artists.utils.Cache;
import me.tehcpu.artists.utils.Parser;
import me.tehcpu.artists.utils.Provider;

/**
 * Created by codebreak on 19/04/16.
 */
public class MainFragment extends Fragment {

    private static MainFragment instance;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ArtistsAdapter mAdapter;
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
        Bundle bundle = getArguments();
//        childname = bundle.getString("data");

        final ArrayList<Artist> data = new ArrayList<>();

        Artist art = new Artist(1080505, "Tove Lo", "pop, dance, electronics", 81, 22, "http://www.tove-lo.com/", "шведская певица и автор песен. Она привлекла к себе внимание в 2013 году с выпуском сингла «Habits», но настоящего успеха добилась с ремиксом хип-хоп продюсера Hippie Sabotage на эту песню, который получил название «Stay High». 4 марта 2014 года вышел её дебютный мини-альбом Truth Serum, а 24 сентября этого же года дебютный студийный альбом Queen of the Clouds. Туве Лу является автором песен таких артистов, как Icona Pop, Girls Aloud и Шер Ллойд.", new ArtistCover("http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/300x300", "http://avatars.yandex.net/get-music-content/dfc531f5.p.1080505/1000x1000"));

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.artists_view);
        rv.setHasFixedSize(true);
        final ArtistsAdapter adapter = new ArtistsAdapter();
        rv.setAdapter(adapter);

        Parser.getInstance().getData(new Parser.onDataParsed() {
            @Override
            public void success(ArrayList<Artist> data) {
                Log.d(TAG, data.size()+"asdasd");
                adapter.addAll(data);
            }

            @Override
            public void failed(JSONObject response) {
                Log.d(TAG, data.size()+"asdasd");
            }
        });

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
