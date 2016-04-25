package me.tehcpu.artists.ui;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by codebreak on 19/04/16.
 */
public class CustomToolbar extends LinearLayoutCompat {
    private final RelativeLayout toolbarLayout;
    private String TAG = "CustomToolbar";

    public CustomToolbar(Context context, RelativeLayout toolbarLayout) {
        super(context);
        this.toolbarLayout = toolbarLayout;
    }

    public void setTitle(String title) {
        TextView titleLayout = (TextView) toolbarLayout.findViewWithTag("toolbarTitle");
        titleLayout.setText(title);
    }

    public void setBackButton() {
        ImageButton backButton = (ImageButton) toolbarLayout.findViewWithTag("toolbarBack");
        backButton.setVisibility(VISIBLE);
    }
}
