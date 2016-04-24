package me.tehcpu.artists.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import me.tehcpu.artists.ArtistsApplication;
import me.tehcpu.artists.R;
import me.tehcpu.artists.RootActivity;
import me.tehcpu.artists.model.Artist;

/**
 * Created by codebreak on 22/04/16.
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistHolder> {
    private static String TAG = "MyRecyclerViewAdapter";
    private ArrayList<Artist> mDataset;
    private static MyClickListener myClickListener;

    public ArtistsAdapter() {
        mDataset = new ArrayList<>();
    }

    public static class ArtistHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView cover;
        TextView name;
        TextView genres;
        TextView summary;

        public ArtistHolder(View itemView) {
            super(itemView);
            cover = (ImageView) itemView.findViewById(R.id.artist_small_cover);
            name = (TextView) itemView.findViewById(R.id.artist_name);
            genres = (TextView) itemView.findViewById(R.id.artist_genres);
            summary = (TextView) itemView.findViewById(R.id.artist_summary);

            Log.d(TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public ArtistsAdapter(ArrayList<Artist> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ArtistHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);
        ArtistHolder dataObjectHolder = new ArtistHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ArtistHolder holder, int position) {
        Artist artist = mDataset.get(position);
        Picasso.with(RootActivity.getInstance().getApplicationContext()).load(artist.getCover().getSmall()).placeholder(R.drawable.account).into(holder.cover);
        holder.name.setText(artist.getName());
        holder.genres.setText(artist.getGenres());
        holder.summary.setText(artist.getAlbums()+" "+artist.getAlbumsHuman()+", "+artist.getTracks()+" "+artist.getTracksHuman());
    }

    public void addItem(Artist dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void addAll(ArrayList<Artist> data) {
        mDataset.addAll(data);
        Log.d(TAG, "qweqwe");
        notifyDataSetChanged();
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public Artist getItem(int position) {
        return mDataset.get(position);
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}