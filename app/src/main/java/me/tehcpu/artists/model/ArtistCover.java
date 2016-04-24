package me.tehcpu.artists.model;

import java.io.Serializable;

/**
 * Created by codebreak on 21/04/16.
 */
public class ArtistCover implements Serializable {
    private String small;
    private String big;

    public ArtistCover(String small, String big) {
        this.small = small;
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }
}
