package com.example.radiogrouptest.occl;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.radiogrouptest.R;
import com.example.radiogrouptest.activity.MainActivity;
import com.example.radiogrouptest.pccl.MyPageCheckedChangeListener;
/**
 * Created by KunGe on 2017/8/22.
 * 该类实现当viewPage页面改变时，radiobutton做相应变化
 */

public class MyOnCheckedChangeListener implements ViewPager.OnPageChangeListener{

    private RadioGroup radioGroup;
    private Handler handler=null;

    public MyOnCheckedChangeListener(RadioGroup radioGroup, Handler mHandler) {
        this.radioGroup = radioGroup;
        this.handler = mHandler;
    }

    public MyOnCheckedChangeListener(RadioGroup radioGroup) {
        this.radioGroup = radioGroup;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(final int position) {
        if (position < 0)
            return;

/*********************************************************************************************************************
 *  向UI界面发送消息，根据当前页面信息更新标题显示，此部分代码和MyOnCheckedChangeListener中原理一样，经实际测试，效率相同  *
 * *******************************************************************************************************************/
        if(this.handler != null) {
            final RadioButton radioButton = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
            //向UI界面发送消息，更新标题
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d(MainActivity.TAG, "onPageSelected THREAD RUN ...");
                    Message msg = new Message();
                    msg.what = MainActivity.UPDATE_TITLE;
                    Bundle bundle = new Bundle();
                    bundle.putString("titel", radioButton.getText().toString());
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }).start();
        }
/*******************************************************************************************************************/

        switch (position) {
            case MyPageCheckedChangeListener.ITEM_CAMERA:
                radioGroup.check(R.id.radioButton_Camera);
                break;
            case MyPageCheckedChangeListener.ITEM_HOMEPAGE:
                radioGroup.check(R.id.radioButton_HomePage);
                break;
            case MyPageCheckedChangeListener.ITEM_ABOUT:
                radioGroup.check(R.id.radioButton_About);
                break;
            default:break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
