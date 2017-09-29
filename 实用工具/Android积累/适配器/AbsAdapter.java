package com.qf.understandmusic.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 16-1-16.
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
    public Context context;
    private List<T> datas;
    private int LayoutID;

    public AbsAdapter(Context context, List<T> datas,int LayoutID) {
        this.context = context;
        this.datas = datas;
        this.LayoutID=LayoutID;
    }
    public void addData(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
    public void refreshData(List<T> datas){
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
    public T getItem(int position) {
        return datas.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(LayoutID,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        showItemContenData(viewHolder, datas.get(position),parent);
        return convertView;
    }
    public abstract void showItemContenData(ViewHolder viewHolder,T data,ViewGroup parent);

    public  class ViewHolder {
        private SparseArray<View> viewCaches = new SparseArray<>();//已经查找到的UI控件的缓存
        public View itemmView;

        private ViewHolder(View itemView) {
            this.itemmView = itemView;
        }
        public View getLayoutView(){
            return itemmView;
        }
        public GridView getItemGridView(int id){
            GridView gv = (GridView) getChildView(id);

            return gv;
        }

        private View getChildView(int itemChildViewId) {
            View view = viewCaches.get(itemChildViewId);

            if (view == null) {
                view = itemmView.findViewById(itemChildViewId);
                if (view != null) {
                    viewCaches.put(itemChildViewId, view);
                }
            }
            return view;
        }

        public void setTextViewContent(int itemChildViewId, String content) {
            TextView childView = (TextView) getChildView(itemChildViewId);
            if (childView != null) {
                childView.setText(content);
            }
        }
        public LinearLayout getLinearLayout(int itemChildViewId){
            LinearLayout childView = (LinearLayout) getChildView(itemChildViewId);
            return childView;
        }
        public ImageView getImageView(int itemChildViewId){
            ImageView childView = (ImageView) getChildView(itemChildViewId);
            return childView;
        }

        public RelativeLayout getRelativeLayout(int itemChildViewId){
            RelativeLayout childView = (RelativeLayout) getChildView(itemChildViewId);
            return childView;
        }


        public void setImageViewContent(int itemChildViewId, String url) {

            final ImageView childView = (ImageView) getChildView(itemChildViewId);

            if (childView != null) {

                x.image().bind(childView, url, ImageOptionsUtil.getImagesOption(), new Callback.CommonCallback<Drawable>() {
                    @Override
                    public void onSuccess(Drawable result) {

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Toast.makeText(childView.getContext(),"cucuo",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }

        }


    }
}
