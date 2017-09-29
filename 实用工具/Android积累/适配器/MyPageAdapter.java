package com.qf.understandmusic.fragment_tuijian.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17 0017.
 */
public class MyPageAdapter extends PagerAdapter {

    private List<View> datas;

    public MyPageAdapter(List<View> datas) {
        this.datas = datas;
    }
    public void addData(List<View> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    public void refreshData(List<View> datas){
        this.datas=datas;
        notifyDataSetChanged();
    }
    public void clearData(){
        this.datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(datas.get(position));
        return datas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }
}
