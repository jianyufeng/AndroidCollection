package com.example.fnshoot;

import java.util.ArrayList;
import java.util.List;



import com.example.fnshoot.bbs.FragmentBbs;
import com.example.fnshoot.news.ZXFragment;
import com.example.fnshoot.pic.PICFragment;
import com.example.fnshoot.set.Activity_Settings;
import com.example.utils.ShortCut;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends FragmentActivity {

	private RadioGroup group;
	
	public List<Fragment> fragments = new ArrayList<Fragment>();
	
	private long currentTime;
	private long prevTime;
	private FrameLayout mFrameTv;
	private ImageView iv_off;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		group = (RadioGroup) findViewById(R.id.mainRadioTabs);
		/*group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int id) {
				changeFragment(id);
			}
		});*/
		mFrameTv = (FrameLayout) findViewById(R.id.fl_off);
		iv_off=(ImageView) findViewById(R.id.iv_off);
		fragments.add(new ZXFragment());
		fragments.add(new PICFragment());
		fragments.add(new FragmentBbs());
		fragments.add(new Activity_Settings());
		group.setOnCheckedChangeListener(new FragmentTabAdapter(this, fragments,R.id.main_contents_fl, group));
		
		//getSupportFragmentManager().beginTransaction().replace(R.id.main_contents_fl,new ZXFragment()).commit();

	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		// 创建桌面快捷图标
		ShortCut.CreateShortCut(MainActivity.this);
	}
	
	/*
	public void changeFragment(int id) {
		switch (id) {
		case R.id.mainRadio_news:
			
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_contents_fl, new ZXFragment()).commit();
			break;
		case R.id.mainRadio_pic:
			
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_contents_fl, new PICFragment()).commit();
			break;
		case R.id.mainRadio_bbs:
			
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_contents_fl, new FragmentBbs()).commit();
			break;
		case R.id.mainRadio_set:
		
			getSupportFragmentManager().beginTransaction()
			.replace(R.id.main_contents_fl, new Activity_Settings()).commit();
			break;

		default:
			break;
		}
	}*/
	
	
	class FragmentTabAdapter implements RadioGroup.OnCheckedChangeListener{
	    private List<Fragment> fragments; // 一个tab页面对应一个Fragment
	    private RadioGroup rgs; // 用于切换tab
	    private FragmentActivity fragmentActivity; // Fragment所属的Activity
	    private int fragmentContentId; // Activity中所要被替换的区域的id

	    private int currentTab; // 当前Tab页面索引

	    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

	    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
	        this.fragments = fragments;
	        this.rgs = rgs;
	        this.fragmentActivity = fragmentActivity;
	        this.fragmentContentId = fragmentContentId;
	        // 默认显示第一页
	        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
	        ft.add(fragmentContentId, fragments.get(0));
	        ft.commit();
	        rgs.setOnCheckedChangeListener(this);
	    }
	    @Override
	    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
	        for(int i = 0; i < rgs.getChildCount(); i++){
	            if(rgs.getChildAt(i).getId() == checkedId){
	                Fragment fragment = fragments.get(i);
	                FragmentTransaction ft = obtainFragmentTransaction(i);
	                getCurrentFragment().onPause(); // 暂停当前tab
	                if(fragment.isAdded()){
	                    fragment.onResume(); // 启动目标tab的onResume()
	                }else{
	                    ft.add(fragmentContentId, fragment);
	                }
	                showTab(i); // 显示目标tab
	                ft.commit();
	                // 如果设置了切换tab额外功能功能接口
	                if(null != onRgsExtraCheckedChangedListener){
	                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
	                }
	            }
	        }
	    }
	    
	    /**
	     * 切换tab
	     * @param idx
	     */
	    private void showTab(int idx){
	        for(int i = 0; i < fragments.size(); i++){
	            Fragment fragment = fragments.get(i);
	            FragmentTransaction ft = obtainFragmentTransaction(idx);

	            if(idx == i){
	                ft.show(fragment);
	            }else{
	                ft.hide(fragment);
	            }
	            ft.commit();
	        }
	        currentTab = idx; // 更新目标tab为当前tab
	    }

	    /**
	     * 获取一个带动画的FragmentTransaction
	     * @param index
	     * @return
	     */
	    private FragmentTransaction obtainFragmentTransaction(int index){
	        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
	        // 设置切换动画
	        if(index > currentTab){
	            ft.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
	        }else{
	            ft.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
	        }
	        return ft;
	    }
	    
	    public int getCurrentTab() {
	        return currentTab;
	    }

	    public Fragment getCurrentFragment(){
	        return fragments.get(currentTab);
	    }

	    public OnRgsExtraCheckedChangedListener getOnRgsExtraCheckedChangedListener() {
	        return onRgsExtraCheckedChangedListener;
	    }

	    public void setOnRgsExtraCheckedChangedListener(OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
	        this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
	    }

	    /**
	     *  切换tab额外功能功能接口
	     */
	     class OnRgsExtraCheckedChangedListener{
	        public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index){
	        }
	    }
	} 
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (event.getAction()==KeyEvent.ACTION_DOWN&&keyCode==KeyEvent.KEYCODE_BACK) {
			if (event.getRepeatCount()==0) {
				currentTime=System.currentTimeMillis();
				if (currentTime-prevTime<=3000) {
					//隐藏底部RadioGroup
					group.setVisibility(View.INVISIBLE);
					mFrameTv.setVisibility(View.VISIBLE);
					iv_off.setVisibility(View.VISIBLE);
			        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tv_off);
			        anim.setAnimationListener(new tvOffAnimListener());
			        iv_off.startAnimation(anim);//加载退出动画 
			        /*defaultFinish();*/
			        return false;
				}
			}
		}
		return true;
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (event.getAction()==KeyEvent.ACTION_UP&&keyCode==KeyEvent.KEYCODE_BACK) {
			prevTime=currentTime;
			Toast.makeText(MainActivity.this, "再按一次退出程序",1).show();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	
	public void defaultFinish(){
		super.finish();
			
	}
	 class tvOffAnimListener implements AnimationListener {
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            defaultFinish();
	        }
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        }
	        @Override
	        public void onAnimationStart(Animation animation) {
	        }
	    }
}
