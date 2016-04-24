package me.tehcpu.artists;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.tehcpu.artists.adapter.CustomPagerAdapter;
import me.tehcpu.artists.fragment.MainFragment;
import me.tehcpu.artists.fragment.SingleArtistFragment;
import me.tehcpu.artists.model.Artist;
import me.tehcpu.artists.ui.CustomPagerTransformer;
import me.tehcpu.artists.ui.CustomViewPager;

public class RootActivity extends AppCompatActivity {


    private static RootActivity instance;
    private FragmentManager fragmentManager;
    private CustomPagerAdapter pagerAdapter;
    private CustomViewPager viewPager;
    private String TAG = "RootActivity";
    private LinearLayout helper;
    private TextView helperText;

    // common

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_root);

        fragmentManager = getSupportFragmentManager();

        pagerAdapter = new CustomPagerAdapter(fragmentManager, getApplicationContext());

        viewPager = (CustomViewPager) findViewById(R.id.viewPager);
        helper = (LinearLayout) findViewById(R.id.root_helper);
        helperText = (TextView) findViewById(R.id.root_helper_text);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, new CustomPagerTransformer());

        viewPager.setOffscreenPageLimit(2);

        pagerAdapter.addFragment(MainFragment.getInstance());
        pagerAdapter.notifyDataSetChanged();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                Log.d(TAG, "position --> "+position);
                if (position == 0) {
                            pagerAdapter.removeFragment(1);
                            pagerAdapter.notifyDataSetChanged();
                } else {
                    CustomViewPager.setCondition(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (savedInstanceState != null) {
            Log.d(TAG, savedInstanceState.getInt("item")+" <-- " + "pagerC --> "+ pagerAdapter.getCount());
            if (savedInstanceState.getInt("item") > 0) {
                pagerAdapter.addFragment(SingleArtistFragment.getInstance());
                pagerAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(savedInstanceState.getInt("item"));
            }
        }

        instance = this;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState, curI -->"+ viewPager.getCurrentItem());
        outState.putInt("item", viewPager.getCurrentItem());
    }

    // Fragments

    public void startSingleArtist(Artist artist) {
        Log.d(TAG, "kqweqweqwe");
        Bundle bundle = new Bundle();
        bundle.putSerializable("artist", artist);
        SingleArtistFragment fragmentChild = new SingleArtistFragment();
        fragmentChild.setArguments(bundle);
        pagerAdapter.addFragment(fragmentChild);
        pagerAdapter.notifyDataSetChanged();

        viewPager.setCurrentItem(pagerAdapter.getCount()+1, true);

        fragmentChild.updateScreen(artist);

        CustomViewPager.setCondition(false);
    }

    public void finishSingleArtist() {
        viewPager.setCurrentItem(0, true);
    }

    public static RootActivity getInstance() {
        if (instance == null) instance = new RootActivity(); // not real, but just in case
        return instance;
    }

    // helper stuff (fake title)

    public void animateHelper(float translationX, float opacity, String text) {
        helper.setTranslationX(translationX);
        helper.setAlpha(opacity);
        helperText.setText(text);
    }

    public void enableHelper() {
        helper.setVisibility(View.VISIBLE);
    }

    public void disableHelper() {
        helper.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        if (pagerAdapter.getCount() > 1) {
            viewPager.setCurrentItem(0, true);
        } else {
            finish();
        }
    }

}
