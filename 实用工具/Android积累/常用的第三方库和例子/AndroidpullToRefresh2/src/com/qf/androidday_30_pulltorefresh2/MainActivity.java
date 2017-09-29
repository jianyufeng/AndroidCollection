package com.qf.androidday_30_pulltorefresh2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;


public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	private PullToRefreshListView listView;
	private List<String> list;
	private ArrayAdapter<String> adapter;
	private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(PullToRefreshListView) findViewById(R.id.lv);
        ListView lv = listView.getRefreshableView();
        lv.addHeaderView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.abc_simple_decor, null));
        list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
			list.add("item"+i);
		}
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setMode(com.handmark.pulltorefresh.library.PullToRefreshBase.Mode.BOTH);//下拉和上拉；
        listView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO 下拉刷新
				new Thread(){
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						list.add(0, "新数据"+new Date());
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								adapter.notifyDataSetChanged();
								listView.onRefreshComplete();
							}
						});
					};
				}.start();
				
			}

			@Override
			public void onPullUpToRefresh(
					PullToRefreshBase<ListView> refreshView) {
				// TODO 上拉加载
				new Thread(){
					public void run() {
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						list.add("新数据"+new Date()); //使用new Data 标识刷新时间
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								adapter.notifyDataSetChanged();
								listView.onRefreshComplete();
							}
						});
					};
				}.start();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(), list.get(position-1), 1).show();
	}
}
