package com.zitherharpmusic.zhmshort.ui.notification;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.data.Language;
import com.zitherharpmusic.zhmshort.data.PhotoQuality;
import com.zitherharpmusic.zhmshort.ui.artist.Artist;
import com.zitherharpmusic.zhmshort.ui.user.User;
import com.zitherharpmusic.zhmshort.util.MainUtils;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private final Fragment fragment;
    private final List<Artist> artists;

    public NotificationAdapter(Fragment fragment, User user) {
        this.fragment = fragment;
        this.artists = user.getArtists();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_song_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Artist artist = artists.get(position);
        holder.title.setText(String.format("%s đã tải lên một video mới", artist.getName(Language.VIETNAMESE)));
        holder.subtitle.setText(artist.getName(Language.SIMPLIFIED_CHINESE));
        MainUtils.loadImage(fragment.requireActivity(), holder.photo, artist.getPhotoUrl(PhotoQuality.SMALL));
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView photo;
        protected TextView title;
        protected TextView subtitle;

        protected ViewHolder(View view) {
            super(view);
            photo = view.findViewById(R.id.photo);
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
        }
    }
}
