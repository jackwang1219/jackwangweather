package com.example.jackwang_0303.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jack wang_0303 on 2018/7/24.
 */

public class VideoAdapter extends BaseAdapter {
    Context context;
    List<Items> list;

    public VideoAdapter(Context context, List<Items> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.item_video,null);
            holder=new ViewHolder();
            holder.iv=(ImageView)view.findViewById(R.id.video_iv);
            holder.title=(TextView)view.findViewById(R.id.video_title);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        Items items=list.get(i);

        holder.title.setText(items.getContent());
//        Bitmap bitmap=ThumbnailUtils.createVideoThumbnail(items.getPic_url(), 1);
//        holder.iv.setImageBitmap(bitmap);
        Picasso.with(context).load(items.getPic_url()).into(holder.iv);

        return view;
    }
    class  ViewHolder{
        ImageView iv;
        TextView title;
    }
}
