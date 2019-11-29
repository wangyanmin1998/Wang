package com.bwie.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bwie.myapplication.Base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list=new ArrayList<>();
    private List<String>  add=new ArrayList<>();
    private ImageView imageView;


    @Override
    protected void initView(View inflate) {
        tabLayout = inflate.findViewById(R.id.tablayout);
        viewPager = inflate.findViewById(R.id.vp_one);
        imageView = inflate.findViewById(R.id.iv_one);
        //图片点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,100);
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        list.clear();
        add.clear();
        SeccendFragment seccendFragment = new SeccendFragment();
        list.add(seccendFragment);

        OntherFragment fragment1 = OntherFragment.getInstance("最新");
        OntherFragment fragment2 = OntherFragment.getInstance("影视");
        OntherFragment fragment3 = OntherFragment.getInstance("视频");
        OntherFragment fragment4 = OntherFragment.getInstance("我的");
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);

        add.add("首页");
        add.add("最新");
        add.add("影视");
        add.add("视频");
        add.add("我的");
        add.add("我的");
        add.add("我的");
        add.add("我的");


        viewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return add.get(position);
            }
        });

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri data1 = data.getData();
        Bitmap bitmap=null;
        try {
            bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),data1);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
