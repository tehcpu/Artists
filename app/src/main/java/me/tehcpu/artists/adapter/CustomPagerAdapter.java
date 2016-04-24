package me.tehcpu.artists.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by codebreak on 19/04/16.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    private static CustomPagerAdapter instance;
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final FragmentManager manager;
    private Context context;

    public CustomPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context = context;
        this.manager = manager;
        this.instance = this;
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }

    public void removeFragment(int position) {
        mFragmentList.remove(position);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment instantiateItem(ViewGroup container, int position){
        Fragment fragment = getItem(position);
        FragmentTransaction trans = manager.beginTransaction();
        trans.add(container.getId(),fragment,"fragment:"+position);
        trans.commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    public static CustomPagerAdapter getInstance() {
        return instance;
    }
}
