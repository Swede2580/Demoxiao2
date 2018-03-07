package com.example.administrator.demoxiao2;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.administrator.demoxiao2.clazz.Mybean;
import com.example.administrator.demoxiao2.fragment.ShouyeFragment;
import com.example.administrator.demoxiao2.fragment.DingziFragment;
import com.example.administrator.demoxiao2.fragment.WanleFragment;
import com.example.administrator.demoxiao2.fragment.WodeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {


    private FrameLayout realcontent;
    private FrameLayout tabcontent;
    private FragmentTabHost tabhost;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {

        Mybean shouye = new Mybean(R.drawable.shouye, "首页", ShouyeFragment.class);
        Mybean dingzhi = new Mybean(R.drawable.dingzhi, "定制", DingziFragment.class);
        Mybean wanle = new Mybean(R.drawable.wanle, "当地玩乐", WanleFragment.class);
        Mybean wode = new Mybean(R.drawable.wode, "我的", WodeFragment.class);

        List<Mybean> mybeanList = new ArrayList<>();

        mybeanList.add(shouye);
        mybeanList.add(dingzhi);
        mybeanList.add(wanle);
        mybeanList.add(wode);

        tabhost.setup(this, getSupportFragmentManager(), R.id.realcontent);
        inflater = LayoutInflater.from(this);

        for (Mybean mybean : mybeanList) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mybean.getText());
            tabSpec.setIndicator(mybean.getText());
            tabSpec.setIndicator(buildView(mybean));
            tabhost.addTab(tabSpec, mybean.getFragment(), null);
        }

        tabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
    }

    private View buildView(Mybean mybean) {
        View view =inflater.inflate(R.layout.iten,null);
        ImageView Tab_img = (ImageView) view.findViewById(R.id.tab_imageview);
        TextView Tab_txt = (TextView) view.findViewById(R.id.tab_textview);

        Tab_img.setImageResource(mybean.getImage());
        Tab_txt.setText(mybean.getText());
        return view;
    }


    private void initView() {
        realcontent = (FrameLayout) findViewById(R.id.realcontent);
        tabcontent = (FrameLayout) findViewById(android.R.id.tabcontent);
        tabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }
}
