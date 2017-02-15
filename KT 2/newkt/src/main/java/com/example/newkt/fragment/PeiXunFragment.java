package com.example.newkt.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.example.newkt.R;
import com.example.newkt.event.MainEvent;
import com.frame.app.base.fragment.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 一颗赛艇 on 2016/12/1.
 */

public class PeiXunFragment extends BaseFragment {


    @Bind(R.id.tv_peixun_tab1)
    TextView mTab1;
    @Bind(R.id.tv_peixun_tab2)
    TextView mTab2;
    @Bind(R.id.tv_peixun_tab3)
    TextView mTab3;
    @Bind(R.id.tv_peixun_tab4)
    TextView mTab4;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.tv_title)
    TextView tv_title;
    private List<Fragment> mList = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.framgnet_peixun);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mList.add(new BigClassFragment());
        mList.add(new JinengFramgent());
        mList.add(new FootBallFragment());
        mList.add(new TestFragment());
        mTab1.setSelected(true);
        mViewpager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mList.get(position);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewpager.setOffscreenPageLimit(3);

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void initHandler(Message msg) {

    }

    @OnClick(R.id.tv_peixun_tab1)
    public void tab1() {

        mViewpager.setCurrentItem(0);
    }

    @OnClick(R.id.tv_peixun_tab2)
    public void tab2() {
        mViewpager.setCurrentItem(1);
    }
    @OnClick(R.id.tv_peixun_tab3)
    public void tab3() {

        mViewpager.setCurrentItem(2);
    }

    @OnClick(R.id.tv_peixun_tab4)
    public void tab4() {
        mViewpager.setCurrentItem(3);
    }

    @OnClick(R.id.image_refresh)
    public void refresh() {
        EventBus.getDefault().post(new MainEvent(1));
    }

    public void checkTab(int position) {
        switch (position) {
            case 0:
                mTab1.setSelected(true);
                mTab2.setSelected(false);
                mTab3.setSelected(false);
                mTab4.setSelected(false);
                tv_title.setText("大课间");
                break;
            case 1:
                mTab2.setSelected(true);
                mTab1.setSelected(false);
                mTab3.setSelected(false);
                mTab4.setSelected(false);
                tv_title.setText("技能教学");
                break;
            case 2:
                mTab3.setSelected(true);
                mTab2.setSelected(false);
                mTab1.setSelected(false);
                mTab4.setSelected(false);
                tv_title.setText("足球课");
                break;
            case 3:
                mTab4.setSelected(true);
                mTab2.setSelected(false);
                mTab3.setSelected(false);
                mTab1.setSelected(false);
                tv_title.setText("测试");
                break;

        }
    }
}
