package me.tehcpu.artists.model;

import java.io.Serializable;

import me.tehcpu.artists.utils.Common;

/**
 * Created by codebreak on 21/04/16.
 */
public class Artist implements Serializable {

    private long id;
    private String name;
    private String genres;
    private long tracks;
    private long albums;
    private String link;
    private String description;
    private ArtistCover cover;

    public Artist() {

    }

    public Artist(long id, String name, String genres, long tracks, long albums, String link, String description, ArtistCover cover) {
        this.id = id;
        this.name = name;
        this.genres = genres;
        this.tracks = tracks;
        this.albums = albums;
        this.link = link;
        this.description = description;
        this.cover = cover;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public long getTracks() {
        return tracks;
    }

    public void setTracks(long tracks) {
        this.tracks = tracks;
    }

    public long getAlbums() {
        return albums;
    }

    public void setAlbums(long albums) {
        this.albums = albums;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArtistCover getCover() {
        return cover;
    }

    public void setCover(ArtistCover cover) {
        this.cover = cover;
    }

    public String getAlbumsHuman() {
        return Common.formSummary(getAlbums(), "альбом", "альбома", "альбомов");
    }

    public String getTracksHuman() {
        return Common.formSummary(getTracks(), "песня", "песни", "песен");
    }
}