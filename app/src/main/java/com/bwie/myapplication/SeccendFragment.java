package com.bwie.myapplication;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bwie.myapplication.Base.BaseFragment;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.stx.xhb.androidx.XBanner;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeccendFragment extends BaseFragment {

    private int page=1;
    private XBanner xBanner;
    private PullToRefreshListView pullToRefreshListView;
    private List<Bean.ListdataBean> list=new ArrayList<>();
    private RelativeLayout relativeLayout;

    @Override
    protected void initView(View inflate) {
        relativeLayout = inflate.findViewById(R.id.rl);
        xBanner = inflate.findViewById(R.id.xbanner);
        pullToRefreshListView = inflate.findViewById(R.id.list_view);
        pullToRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bean.ListdataBean listdataBean = list.get(position-1);
                String url = listdataBean.getUrl();
                Intent intent = new Intent(getActivity(), SeccendActivity.class);
                intent.putExtra("key",url);
                startActivity(intent);
            }
        });

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_seccend;
    }

    @Override
    protected void initData() {
        //设置上下拉
        pullToRefreshListView.setMode(PullToRefreshListView.Mode.BOTH);
        //设置监听
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                Data();
                list.clear();

                pullToRefreshListView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                Data();
                pullToRefreshListView.onRefreshComplete();

            }
        });






        Data();
    }

    private void Data() {
        if(NetUtil.getInstance().hasNet(getActivity())){
            pullToRefreshListView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

            String httpUrl="";
            if (page==1){
                httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer.json";
            }else if (page==2){
                httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
            }else {
                httpUrl="http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
            }
            NetUtil.getInstance().getinfo(httpUrl, new NetUtil.MyCallBack() {
                @Override
                public void onGetJson(String json) {
                    Gson gson = new Gson();
                    Bean bean = gson.fromJson(json, Bean.class);

                    final List<Bean.BannerdataBean> bannerdata = bean.getBannerdata();
                    if (bannerdata!=null){
                        xBanner.setBannerData(bannerdata);
                        xBanner.loadImage(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {
                                Bean.BannerdataBean bannerdataBean = bannerdata.get(position);
                                String imageUrl = bannerdataBean.getImageUrl();
                                NetUtil.getInstance().getphoto(imageUrl,(ImageView) view);
                            }
                        });

                    }


                    List<Bean.ListdataBean> listdata = bean.getListdata();
                    list.addAll(listdata);
                    pullToRefreshListView.setAdapter(new MyAdapter(list));

                }
            });

        }else {
            pullToRefreshListView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);

        }

    }


}
