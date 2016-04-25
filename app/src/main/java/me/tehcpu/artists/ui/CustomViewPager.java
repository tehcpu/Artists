package me.tehcpu.artists.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by codebreak on 19/04/16.
 */
public class CustomViewPager extends ViewPager {
    private static boolean locked;
    private String TAG = "CustomViewPager";

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        locked = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return !locked && super.onTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return !locked && super.onInterceptTouchEvent(event);
    }

    // To enable/disable swipe
    public static void setCondition(boolean locked) {
        CustomViewPager.locked = locked;
    }
}
