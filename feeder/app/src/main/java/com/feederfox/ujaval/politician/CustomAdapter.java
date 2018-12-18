package com.feederfox.ujaval.politician;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feederfox.ujaval.politician.bean.Politician_bean;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    LayoutInflater inflter;
    Context context;
    ArrayList<Politician_bean> politician_beanArrayList=new ArrayList<Politician_bean>();


    public CustomAdapter(Context applicationContext, ArrayList<Politician_bean> politician_beanArray) {
        this.context = applicationContext;
        this.politician_beanArrayList = politician_beanArray;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return politician_beanArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return politician_beanArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder mViewHolder;
        if (view == null) {
            view = inflter.inflate(R.layout.row, viewGroup, false);
            mViewHolder = new MyViewHolder(view);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) view.getTag();
        }

        Politician_bean currentListData = (Politician_bean) getItem(i);

        mViewHolder.tvTitle.setText(currentListData.getPolitic_Name());
        Glide.with(context).load(currentListData.getImage()).override(400, 100).fitCenter().into(mViewHolder.imageView);

        return view;
    }


    private class MyViewHolder {
        TextView tvTitle;
        ImageView imageView;
        public MyViewHolder(View item) {
            tvTitle = (TextView) item.findViewById(R.id.tvTitle);
            imageView=(ImageView) item.findViewById(R.id.imageView);
        }
    }
}