package com.example.radiogrouptest.pccl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.radiogrouptest.R;
import com.example.radiogrouptest.activity.MainActivity;

/**
 * Created by KunGe on 2017/8/22.
 * 该类用于处理当页面发生改变时，显示相应的fragment
 */

public class MyPageCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

    public static final int ITEM_CAMERA = 0;
    public static final int ITEM_HOMEPAGE = 1;
    public static final int ITEM_ABOUT = 2;

    private ViewPager viewPager;
    private Handler handler=null;

    public MyPageCheckedChangeListener(ViewPager viewPager, Handler mHandler) {
        this.viewPager = viewPager;
        this.handler = mHandler;
    }

    public MyPageCheckedChangeListener(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCheckedChanged(final RadioGroup radioGroup, @IdRes final int i) {

        if (i<0)
            return;
/*********************************************************************************************************************
 *  向UI界面发送消息，根据当前页面信息更新标题显示，此部分代码和MyPageCheckedChangeListener中原理一样，经实际测试，效率相同  *
 * *******************************************************************************************************************/
//        //向UI界面发送消息，更新标题
        if(this.handler != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(MainActivity.TAG, "onCheckedChanged THREAD RUN ...");
                    RadioButton radioButton = radioGroup.findViewById(i);
                    Message msg = new Message();
                    msg.what = MainActivity.UPDATE_TITLE;
                    Bundle bundle = new Bundle();
                    bundle.putString("titel", radioButton.getText().toString());
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }).start();
        }
/*********************************************************************************************************************/
        switch (i){
            case R.id.radioButton_Camera:
                viewPager.setCurrentItem(ITEM_CAMERA, false);
                break;
            case R.id.radioButton_HomePage:
                viewPager.setCurrentItem(ITEM_HOMEPAGE, false);
                break;

            case R.id.radioButton_About:
                viewPager.setCurrentItem(ITEM_ABOUT, false);
                break;
            default:break;
        }
    }
}
