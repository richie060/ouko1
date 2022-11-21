package com.example.mentalhealth.player;

import android.app.Application;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Log;

import mentalhealth.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    ExoPlayer exoPlayer;
    PlayerView playerView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAdapterPosition());
            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                mClickListener.onItemLongClick(view,getAdapterPosition());
                return false;
            }
        });
    }

    public void setExoplayer(Application application ,String name,String Videourl){
        TextView textView = itemView.findViewById(R.id.tv_item_name);
        playerView = itemView.findViewById(R.id.exoplayer_item);

        textView.setText(name);

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(application).build();
//            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

            exoPlayer = new ExoPlayer.Builder(application).build();
            Uri video = Uri.parse(Videourl);

//            initialize data source

            DefaultHttpDataSource dataSourceFactory = new DefaultHttpDataSource("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

//            initialize media source


            MediaSource mediaSource = new ProgressiveMediaSource.Factory(new DefaultHttpDataSource.Factory())
                    .createMediaSource(MediaItem.fromUri(video));

//            set player
            playerView.setPlayer(exoPlayer);

//            prepare media
            exoPlayer.prepare(mediaSource);

//            play video when ready
            exoPlayer.setPlayWhenReady(false);

        }catch (Exception e){
            Log.e("ViewHolder","exoplayer error"+e.toString());
        }
    }
    private Clicklistener mClickListener;
    public interface Clicklistener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view ,int position);
    }
    public void setOnClicklistener(Clicklistener clicklistener){
        mClickListener = clicklistener;
    }
}
