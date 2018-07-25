package com.example.jackwang_0303.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Items> list = new ArrayList<Items>();
    ListView lv;
    //api地址
    String path = "http://m2.qiushibaike.com/article/list/video?page=2&count=30&readarticles=[115762484,115762135,115764350,115761463,115760316,115764445,115763537,115758684]&rqcnt=17&r=804df97a1459411164081";
    private DrawerLayout drawerLayout;
    //handler 用来解析json并做其他处理
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String s = (String) msg.obj;
                VideoBean bean = new Gson().fromJson(s, VideoBean.class);
                list = bean.getItems();
                VideoAdapter adapter = new VideoAdapter(MainActivity.this, list);
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(listener);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例toolbar并代替actionbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //实例侧滑
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        //header的提示
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_nav);

        }
        //nav实例化
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);

        //列表的初始化
        lv = (ListView) findViewById(R.id.video_lv);
        //设置侧滑列表的点击事件
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });

//        ContentResolver resolver=getContentResolver();
//        Uri videoUri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//        Cursor cursor=resolver.query(videoUri,null,null,null,null);
//        while (cursor.moveToNext()){
//            String name=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
//            String path=cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
//            VideoBean bean=new VideoBean(name,path);
//            list.add(bean);
//
//        }


//        VideoAdapter adapter=new VideoAdapter(MainActivity.this,list);
//        lv.setAdapter(adapter);
        //新线程 用来传递json
        new Thread(new Runnable() {
            @Override
            public void run() {
                String json = HttpUtils.getJson(path);
                Message msg = new Message();
                msg.what = 1;
                msg.obj = json;
                handler.sendMessage(msg);
            }
        }).start();
        //设置悬浮按钮的点击事件
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Deleted",Snackbar.LENGTH_SHORT).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,"Data restored",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            Items bean = list.get(i);

            Intent intent = new Intent();
            intent.setClass(MainActivity.this, VideoActivity.class);
            intent.putExtra("path", bean.getLow_url());
            startActivity(intent);
        }
    };
    //左上角按钮点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
//                break;
        }
        return  true;
    }
}
