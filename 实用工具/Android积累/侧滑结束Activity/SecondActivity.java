package com.qfxa.day37_qq;

import android.os.Bundle;

/**
 * Created by Administrator on 2016/2/29 0029.
 */
public class SecondActivity extends SwipeActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blk);
        setSwipeAnyWhere(true);
    }
}
