package com.qf.androidday_30_01_slidingmenu;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnCloseListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.OnOpenedListener;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	private SlidingMenu sMenu;// 定义一个slidingmenu
	private DisplayMetrics dMetrics;// 屏幕测量工具
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.tvID);
		dMetrics = getResources().getDisplayMetrics();

		sMenu = new SlidingMenu(this);
		sMenu.setMode(SlidingMenu.LEFT_RIGHT);// 设置抽拉模式
		
		sMenu.setMenu(R.layout.left_menu);// 设置menu
		
		sMenu.setSecondaryMenu(R.layout.right_menu); //给右menu设置布局
		
		sMenu.setShadowWidth(100);
		sMenu.setShadowDrawable(R.drawable.ic_launcher);
		sMenu.setFadeDegree(1.0F);
//		sMenu.setFadeEnabled(true);
		
		sMenu.setBehindScrollScale(0.5f);
		
		
		sMenu.setBehindWidth((int) (dMetrics.widthPixels * 0.3));// 设置slidingMenu的宽度
		sMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置slidingmenu的滑动拉出方式，此处是全屏拉出
		sMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		sMenu.findViewById(R.id.btn1ID).setOnClickListener(this);
		sMenu.findViewById(R.id.btn2ID).setOnClickListener(this);
		sMenu.findViewById(R.id.btn3ID).setOnClickListener(this);
		sMenu.setOnOpenedListener(new OnOpenedListener() {
			
			@Override
			public void onOpened() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "opened", Toast.LENGTH_SHORT).show();
				textView.setBackgroundColor(Color.GREEN);
			}
		});
		sMenu.setOnOpenListener(new OnOpenListener() {
			
			@Override
			public void onOpen() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "open", Toast.LENGTH_SHORT).show();
				textView.setBackgroundColor(Color.RED);
			}
		});
		sMenu.setOnClosedListener(new OnClosedListener() {
			
			@Override
			public void onClosed() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "closed", Toast.LENGTH_SHORT).show();
			textView.setBackgroundColor(Color.BLACK);
			}
		});
		sMenu.setOnCloseListener(new OnCloseListener() {
			
			@Override
			public void onClose() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "close", Toast.LENGTH_SHORT).show();
				textView.setBackgroundColor(Color.BLUE);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1ID:
			textView.setText("侧滑菜单1");
			break;
		case R.id.btn2ID:
			textView.setText("侧滑菜单2");
			setTitle("nimabi");
			break;
		case R.id.btn3ID:
			textView.setText("侧滑菜单3");
			setTitle("nimabide");
			break;

		default:
			break;
		}
	}
}
