package me.tehcpu.artists.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.tehcpu.artists.RootActivity;

/**
 * Created by codebreak on 19/04/16.
 */
public class CustomPagerAdapter extends FragmentPagerAdapter {
    private static volatile CustomPagerAdapter Instance;
    private final ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private final FragmentManager manager;

    public CustomPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.manager = manager;
        this.Instance = this;
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
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    public static CustomPagerAdapter getInstance() {
        CustomPagerAdapter localInstance = Instance;
        if (localInstance == null) {
            synchronized (CustomPagerAdapter.class) {
                localInstance = Instance;
                if (localInstance == null) {
                    RootActivity rootInstance = RootActivity.getInstance();
                    Instance = localInstance = new CustomPagerAdapter(rootInstance.getSupportFragmentManager(), rootInstance.getApplicationContext());
                }
            }
        }
        return localInstance;
    }
}
