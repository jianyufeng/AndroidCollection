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
		// ����������ͼ��
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
	    private List<Fragment> fragments; // һ��tabҳ���Ӧһ��Fragment
	    private RadioGroup rgs; // �����л�tab
	    private FragmentActivity fragmentActivity; // Fragment������Activity
	    private int fragmentContentId; // Activity����Ҫ���滻�������id

	    private int currentTab; // ��ǰTabҳ������

	    private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // �����õ��������л�tabʱ�������µĹ���

	    public FragmentTabAdapter(FragmentActivity fragmentActivity, List<Fragment> fragments, int fragmentContentId, RadioGroup rgs) {
	        this.fragments = fragments;
	        this.rgs = rgs;
	        this.fragmentActivity = fragmentActivity;
	        this.fragmentContentId = fragmentContentId;
	        // Ĭ����ʾ��һҳ
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
	                getCurrentFragment().onPause(); // ��ͣ��ǰtab
	                if(fragment.isAdded()){
	                    fragment.onResume(); // ����Ŀ��tab��onResume()
	                }else{
	                    ft.add(fragmentContentId, fragment);
	                }
	                showTab(i); // ��ʾĿ��tab
	                ft.commit();
	                // ����������л�tab���⹦�ܹ��ܽӿ�
	                if(null != onRgsExtraCheckedChangedListener){
	                    onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(radioGroup, checkedId, i);
	                }
	            }
	        }
	    }
	    
	    /**
	     * �л�tab
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
	        currentTab = idx; // ����Ŀ��tabΪ��ǰtab
	    }

	    /**
	     * ��ȡһ����������FragmentTransaction
	     * @param index
	     * @return
	     */
	    private FragmentTransaction obtainFragmentTransaction(int index){
	        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
	        // �����л�����
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
	     *  �л�tab���⹦�ܹ��ܽӿ�
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
					//���صײ�RadioGroup
					group.setVisibility(View.INVISIBLE);
					mFrameTv.setVisibility(View.VISIBLE);
					iv_off.setVisibility(View.VISIBLE);
			        Animation anim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.tv_off);
			        anim.setAnimationListener(new tvOffAnimListener());
			        iv_off.startAnimation(anim);//�����˳����� 
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
			Toast.makeText(MainActivity.this, "�ٰ�һ���˳�����",1).show();
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
