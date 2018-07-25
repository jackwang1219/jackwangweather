package com.example.jackwang_0303.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {


    VideoView videoView;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView=(VideoView) findViewById(R.id.video_vv);
        path = getIntent().getStringExtra("path");
        videoView.setVideoPath(path);
        //设置控制条
        videoView.setMediaController(new MediaController(VideoActivity.this));
        //自动播放
        videoView.start();



    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止播放

        videoView.stopPlayback();

    }
}
