package me.tehcpu.artists.ui;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.tehcpu.artists.RootActivity;

/**
 * Created by codebreak on 17/04/16.
 */
public class CustomPagerTransformer implements ViewPager.PageTransformer {
    private String TAG = "CustomPagerTransformer";

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            view.setAlpha(1+position);

            // init section
            RootActivity root = RootActivity.getInstance();

            RelativeLayout content = (RelativeLayout) view.findViewWithTag("customContent");
            RelativeLayout toolbar = (RelativeLayout) view.findViewWithTag("customToolbar");
            TextView toolbarTitle = (TextView) toolbar.findViewWithTag("toolbarTitle");

            float rootX = pageWidth * position / 22;
            root.animateHelper(rootX, position+1, toolbarTitle.getText().toString());

            if (rootX == 0.0 || rootX > -1) {
                root.disableHelper();
                toolbar.setAlpha(position+1);
            } else {
                root.enableHelper();
                toolbar.setAlpha(0);
            }

            content.setTranslationX(pageWidth * -position/2);
            toolbar.setTranslationX((float) (pageWidth * -position/1.05));
        } else if (position <= 1) { // (0,1]

            // init section
            view.setTranslationX((float) (pageWidth * -position));
            RelativeLayout content = (RelativeLayout) view.findViewWithTag("customContent");
            RelativeLayout toolbar = (RelativeLayout) view.findViewWithTag("customToolbar");

            toolbar.setAlpha((float) (1 - position*1.2));
            content.setTranslationX((float) (pageWidth * position));

            content.setTranslationX((float) (pageWidth * position));
            toolbar.setTranslationX((float) (pageWidth * position/4));
        } else { // (1,+Infinity]
            view.setAlpha(0);
        }
    }
}
