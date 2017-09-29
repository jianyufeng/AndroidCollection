package com.qf.understandmusic.fragment_tuijian.playavtivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.qf.understandmusic.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * Created by Administrator on 2016/1/25 0025.
 */
public class PlayActivityMV extends Activity {

    @ViewInject(R.id.play_videoView)
    private VideoView videoView;
    private String path;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playdianying);
        x.view().inject(this);
        Intent intent = getIntent();
        path = intent.getStringExtra("path");
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoPath(path);
        videoView.start();
    }


}
