package com.example.administrator.demoxiao2.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demoxiao2.R;
import com.example.administrator.demoxiao2.adapter.FragmentAdapter;
import com.example.administrator.demoxiao2.fragment1.DongnanFragment;
import com.example.administrator.demoxiao2.fragment1.HaidaoFragment;
import com.example.administrator.demoxiao2.fragment1.OuFragment;
import com.example.administrator.demoxiao2.fragment1.QitaFragment;
import com.example.administrator.demoxiao2.fragment1.RihaiFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DingziFragment extends Fragment {


    private TabLayout tablayout;
    private ViewPager vp;
    private List<String> stringList;
    private List<Fragment> fragmentList;

    public DingziFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_qinzi, container, false);
        initView(inflate);
        initData();
        initAdapter();
        return inflate;
    }

    private void initAdapter() {
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(), fragmentList, stringList);
        vp.setAdapter(adapter);

        tablayout.setupWithViewPager(vp);
    }

    private void initData() {
        stringList = new ArrayList<>();
        stringList.add("欧美");
        stringList.add("东南亚");
        stringList.add("海岛");
        stringList.add("日韩");
        stringList.add("澳新");


        fragmentList = new ArrayList<>();
        fragmentList.add(new OuFragment());
        fragmentList.add(new DongnanFragment());
        fragmentList.add(new HaidaoFragment());
        fragmentList.add(new RihaiFragment());
        fragmentList.add(new QitaFragment());
    }

    private void initView(View inflate) {
        tablayout = (TabLayout) inflate.findViewById(R.id.tablayout);
        vp = (ViewPager) inflate.findViewById(R.id.vp);
    }
}
