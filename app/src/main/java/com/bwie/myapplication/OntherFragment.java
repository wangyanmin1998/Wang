package com.bwie.myapplication;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.myapplication.Base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class OntherFragment extends BaseFragment {


    private TextView textView;

    @Override
    protected void initView(View inflate) {
        textView = inflate.findViewById(R.id.tv);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_onther;
    }

    @Override
    protected void initData() {
        String key = getArguments().getString("key");
        textView.setText(key);

    }

    public static OntherFragment getInstance(String value) {
        OntherFragment ontherFragment = new OntherFragment();

        Bundle bundle = new Bundle();

        bundle.putString("key",value);

        ontherFragment.setArguments(bundle);

        return ontherFragment;
    }

}
