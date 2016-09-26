package com.huangssh.colorfulprogress.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huangssh.colorfulprogress.R;
import com.huangssh.colorfulprogress.Util.UtilPhoneParam;
import com.huangssh.colorfulprogress.base.MyFragmentActivity;
import com.huangssh.colorfulprogress.fragment.FlowMealFragment;
import com.huangssh.colorfulprogress.fragment.MessageMealFragment;
import com.huangssh.colorfulprogress.fragment.VoiceMealFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 套餐查询主页
 */
public class MealAllowanceActivity extends MyFragmentActivity {
    private ArrayList<Fragment> fragmentsList;
    private LinearLayout tabChargeCell, tabChargeFlow, tabPrepaidCard;
    private TextView tvMealFlow, tvMealVoice, tvMealMessage;
    private ImageView ivMealFlow, ivMealVoice, ivMealMessage;
    private FlowMealFragment flowMealFragment;// 流量套餐
    private MessageMealFragment messageMealFragment;// 短信套餐
    private VoiceMealFragment voiceMealFragment;// 语音套餐
    private ViewPager mPager;
    private MyFragmentPagerAdapter adapter;
    private List<View> mTabs;
    private MealAllowanceActivity mealAllowanceActivity = this;

    private String titleBarName;

    private double myUsedFlow = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_allowance);
        UtilPhoneParam.init(this);
        initView();
    }

    private void initView() {
        addFramentList();
        tabChargeCell = (LinearLayout)
                findViewById(R.id.tab_charge_cell);
        tabChargeFlow = (LinearLayout)
                findViewById(R.id.tab_charge_flow);
        tabPrepaidCard = (LinearLayout)
                findViewById(R.id.tab_prepaid_card);
        tvMealFlow = findView(R.id.tvMealFlow);;
        tvMealMessage = findView(R.id.tvMealMesage);
        tvMealVoice = findView(R.id.tvMealVoice);
        ivMealFlow = findView(R.id.ivMealFlow);
        ivMealMessage = findView(R.id.ivMealMessage);
        ivMealVoice = findView(R.id.ivMealVoice);

        tabChargeCell.setOnClickListener(new TabOnClickListener(0));
        tabChargeFlow.setOnClickListener(new TabOnClickListener(1));
        tabPrepaidCard.setOnClickListener(new TabOnClickListener(2));
        initViewPager();

        if (this.getIntent() != null){
            int page = this.getIntent().getIntExtra("page", 0);
            setPageOrCode(page);
        }else{
            setPageOrCode(0);
        }
    }

    private void initViewPager() {
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                fragmentsList);
        mPager = (ViewPager) findViewById(R.id.meal_viewpager);
//        mPager.setOffscreenPageLimit(PAGEPRELOAD);
        mPager.setAdapter(adapter);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    public class TabOnClickListener implements OnClickListener {
        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            if (mPager != null){
                mPager.setCurrentItem(index);
            }
        }
    }

    /**
     * 跳转到当前页面
     * @param page 0：流量 1：语音 2：短信
     * @param activity
     */
    public static void goThisActivity(int page, Context activity){
        Intent intent = new Intent(activity, MealAllowanceActivity.class);
        intent.putExtra("page", page);
        activity.startActivity(intent);
    }


    public void setPageOrCode(int page) {
        if (page >= 0 && page <= 2)
            mPager.setCurrentItem(page);
    }


    private void addFramentList() {
        Log.w("Request", "addFramentList");
        fragmentsList = new ArrayList<Fragment>();

        messageMealFragment = new MessageMealFragment(titleBarName);
        flowMealFragment = new FlowMealFragment(titleBarName);
        voiceMealFragment = new VoiceMealFragment(titleBarName);
        fragmentsList.add(flowMealFragment);
        fragmentsList.add(voiceMealFragment);
        fragmentsList.add(messageMealFragment);
    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
//            if (PAGEPRELOAD == 1) {
//                PAGEPRELOAD = 3;
//                mPager.setOffscreenPageLimit(PAGEPRELOAD);
//            }
            switch (arg0) {
                // 流量
                case 0:
                    tvMealFlow.setTextColor(getResources().getColor(R.color.orange1));
                    tvMealVoice.setTextColor(getResources().getColor(R.color.contentColor));
                    tvMealMessage.setTextColor(getResources().getColor(R.color.contentColor));
                    ivMealFlow.setBackgroundResource(R.drawable.ic_meal_flow_press);
                    ivMealVoice.setBackgroundResource(R.drawable.ic_meal_voice_normal);
                    ivMealMessage.setBackgroundResource(R.drawable.ic_meal_message_normal);
                    break;
                // 语音
                case 1:
                    tvMealFlow.setTextColor(getResources().getColor(R.color.contentColor));
                    tvMealVoice.setTextColor(getResources().getColor(R.color.orange1));
                    tvMealMessage.setTextColor(getResources().getColor(R.color.contentColor));
                    ivMealFlow.setBackgroundResource(R.drawable.ic_meal_flow_normal);
                    ivMealVoice.setBackgroundResource(R.drawable.ic_meal_voice_press);
                    ivMealMessage.setBackgroundResource(R.drawable.ic_meal_message_normal);
                    break;
                // 短信
                case 2:
                    tvMealFlow.setTextColor(getResources().getColor(R.color.contentColor));
                    tvMealVoice.setTextColor(getResources().getColor(R.color.contentColor));
                    tvMealMessage.setTextColor(getResources().getColor(R.color.orange1));
                    ivMealFlow.setBackgroundResource(R.drawable.ic_meal_flow_normal);
                    ivMealVoice.setBackgroundResource(R.drawable.ic_meal_voice_normal);
                    ivMealMessage.setBackgroundResource(R.drawable.ic_meal_message_press);
                    break;

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentsList;

        public MyFragmentPagerAdapter(FragmentManager fm,
                                      ArrayList<Fragment> fragments) {
            super(fm);
            this.fragmentsList = fragments;
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentsList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

    }
}
