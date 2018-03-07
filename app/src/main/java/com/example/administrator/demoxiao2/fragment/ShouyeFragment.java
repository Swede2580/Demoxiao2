package com.example.administrator.demoxiao2.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.demoxiao2.MapActivity;
import com.example.administrator.demoxiao2.R;
import com.example.administrator.demoxiao2.adapter.BeanAdapter;
import com.example.administrator.demoxiao2.adapter.ShouyeAdapter;
import com.example.administrator.demoxiao2.clazz.Bean;
import com.example.administrator.demoxiao2.clazz.Shouye;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouyeFragment extends Fragment {


    private TextView txt;
    private EditText edit;
    private Banner banner;
    private GridView grid;
    private ImageView img;
    private List<Bean> beanList;
    private RecyclerView recycler;

    public ShouyeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_guoshi, container, false);
        initView(inflate);
        initData();
        initListener();
        return inflate;
    }

    private void initListener() {
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapActivity.class));
            }
        });
    }

    private void initData() {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic2/10639/20141105163614-1223118510.jpg/0");
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic2/28531/cut-20141105163424-649077356.jpg/0");
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic2/5744/cut-20141105163400-882181576.jpg/0");
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic2/10642/cut-20141105163509-311046641.jpg/0");
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic2/27226/cut-20141105163259-200124067.jpg/0");
        arrayList.add("http://pic.baike.soso.com/ugc/baikepic/31024/cut-20140303113915-424758572.jpg/0");

        List<String> list = new ArrayList<>();
        list.add("哈比");
        list.add("艾露莎");
        list.add("格雷");
        list.add("纳兹");
        list.add("露西");
        list.add("温蒂");
        banner.setImages(arrayList)
                .setDelayTime(2000)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new BitmapPicasso())
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setBannerTitles(list)
                .start();

        beanList = new ArrayList<>();
        beanList.add(new Bean(R.mipmap.icon_main_free_travel, "自由行"));
        beanList.add(new Bean(R.mipmap.icon_main_airticket, "机票"));
        beanList.add(new Bean(R.mipmap.icon_main_visa, "签证"));
        beanList.add(new Bean(R.mipmap.icon_main_destination_travel, "目的地参团"));
        beanList.add(new Bean(R.mipmap.icon_main_half_free, "半自由行"));
        beanList.add(new Bean(R.mipmap.icon_main_hotel, "酒店"));
        beanList.add(new Bean(R.mipmap.icon_main_ticket, "门票"));
        beanList.add(new Bean(R.mipmap.icon_main_other_product, "其他"));

        BeanAdapter adapter = new BeanAdapter(beanList, getActivity());
        grid.setAdapter(adapter);

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url("http://172.16.54.15:8080/json/qinzi.json").build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread(new Runnable() {

                    private List<Shouye.ResultBean.DataBean> data;

                    @Override
                    public void run() {
                        Shouye shouye = new Gson().fromJson(string, Shouye.class);
                        data = shouye.getResult().getData();

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        ShouyeAdapter shouyeAdapter = new ShouyeAdapter(data, getActivity());

                        recycler.setLayoutManager(layoutManager);
                        recycler.setAdapter(shouyeAdapter);
                    }
                });
            }
        });

    }

    private void initView(View inflate) {
        txt = (TextView) inflate.findViewById(R.id.txt);
        edit = (EditText) inflate.findViewById(R.id.edit);
        banner = (Banner) inflate.findViewById(R.id.banner);
        grid = (GridView) inflate.findViewById(R.id.grid);
        img = (ImageView) inflate.findViewById(R.id.img);
        recycler = (RecyclerView) inflate.findViewById(R.id.recycler);

    }

    private void submit() {
        // validate
        String editString = edit.getText().toString().trim();
        if (TextUtils.isEmpty(editString)) {
            Toast.makeText(getContext(), "editString不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    class BitmapPicasso extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Picasso.with(context).load((String) path).into(imageView);
        }
    }
}
