package com.example.radiogrouptest.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.radiogrouptest.R;
import com.example.radiogrouptest.adapter.MyFramentPageAdapter;
import com.example.radiogrouptest.fragment.FragmentAbout;
import com.example.radiogrouptest.fragment.FragmentCamera;
import com.example.radiogrouptest.fragment.FragmentHomePage;
import com.example.radiogrouptest.occl.MyOnCheckedChangeListener;
import com.example.radiogrouptest.pccl.MyPageCheckedChangeListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int UPDATE_TITLE = 0XAA;
    public static final String TAG = "MainActivity";

    private Button buttonHome, buttonCamera, buttonAbout;
    private LinearLayout titalBar;
    private TextView titelText;

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RadioButton radioButtonCamera,radioButtonHomepage, radioButtonAbout;
    private ArrayList<Fragment> fragmentArrayList;


    //用于处理实时标题
    MyHandler myHandler = new MyHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titalBar = (LinearLayout)findViewById(R.id.titlebar);
        titelText = (TextView)titalBar.findViewById(R.id.textView_title);

        initView();             //初始化界面组件
        initViewPager();        //初始化viewPager
    }


    private void initViewPager() {
        FragmentCamera fragmentCamera = new FragmentCamera();
        FragmentHomePage fragmentHomePage = new FragmentHomePage();
        FragmentAbout fragmentAbout = new FragmentAbout();

        //注意：添加顺序与显示顺序是一致的
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(fragmentCamera);
        fragmentArrayList.add(fragmentHomePage);        //角标1 MyPageCheckedChangeListener.ITEM_CAMERA
        fragmentArrayList.add(fragmentAbout);

        //ViewPager设置适配器
        viewPager.setAdapter(new MyFramentPageAdapter(getSupportFragmentManager(), fragmentArrayList));
        //viewPager显示主页fargment
        viewPager.setCurrentItem(MyPageCheckedChangeListener.ITEM_HOMEPAGE);      //这是传入1， 会显示HomePage, 如果注释此句，则会默认显示ArrayList角标为0的Fragment
        //viewPager页面切换监听
        viewPager.addOnPageChangeListener(new MyOnCheckedChangeListener(radioGroup));
    }

    private void initView() {
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButtonAbout = (RadioButton)findViewById(R.id.radioButton_About);
        radioButtonCamera = (RadioButton)findViewById(R.id.radioButton_Camera);
        radioButtonHomepage = (RadioButton)findViewById(R.id.radioButton_Camera);
        //设置RadioButton状态改变监听
        radioGroup.setOnCheckedChangeListener(new MyPageCheckedChangeListener(viewPager, myHandler));
    }



    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TITLE:
                    Bundle bundle = msg.getData();
                    Log.d(TAG, "in myHandler ");
                    MainActivity.this.titelText.setText(bundle.getString("titel"));
                    break;
                default:break;
            }
        }
    }
}
