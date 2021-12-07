package com.zitherharpmusic.zhmshort.ui.video;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zitherharpmusic.zhmshort.R;
import com.zitherharpmusic.zhmshort.util.ListenerUtils;
import com.zitherharpmusic.zhmshort.util.MainUtils;

import java.util.List;

public class VideoGridAdapter extends RecyclerView.Adapter<VideoGridAdapter.ViewHolder> {
    private final FragmentActivity context;
    private final List<Video> videos;

    public VideoGridAdapter(FragmentActivity context, List<Video> videos) {
        this.context = context;
        this.videos = videos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_video_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = videos.get(position);
        holder.videoFavouriteCount.setText(video.getFavouriteCountToString());
        MainUtils.loadImage(context, holder.videoThumbnail, video.getThumbnailUrl(VideoThumbnail.HQDEFAULT));
        holder.videoThumbnail.setOnClickListener(ListenerUtils.launchVideoFullscreenActivity(context, videos, position));
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView videoThumbnail;
        protected TextView videoFavouriteCount;

        public ViewHolder(View view) {
            super(view);
            videoThumbnail = view.findViewById(R.id.video_thumbnail);
            videoFavouriteCount = view.findViewById(R.id.video_favourite_count);
        }
    }
}
