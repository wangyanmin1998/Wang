package com.bwie.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/*
 *@auther:王彦敏
 *@Date: 2019/11/19
 *@Time:20:53
 *@Description:
 * */
class MyAdapter extends BaseAdapter {
    List<Bean.ListdataBean> list;
    public MyAdapter(List<Bean.ListdataBean> list) {
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Bean.ListdataBean listdataBean = list.get(position);
        int type = listdataBean.getType();
        if (type==1){
            return 0;
        }else {
            return 1;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int itemViewType = getItemViewType(position);
        ViewHolder holder=null;
        if (convertView==null){
            if (itemViewType==0){
                convertView=View.inflate(parent.getContext(),R.layout.itme1,null);
                holder=new ViewHolder();
                holder.tv_name=convertView.findViewById(R.id.tv_name);
                holder.iv=convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            }else {
                convertView=View.inflate(parent.getContext(),R.layout.itme2,null);
                holder=new ViewHolder();
                holder.tv_name=convertView.findViewById(R.id.tv_name);
                holder.iv=convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            }

        }else {
            holder = (ViewHolder) convertView.getTag();

        }
        Bean.ListdataBean listdataBean = list.get(position);
        holder.tv_name.setText(listdataBean.getName());
        NetUtil.getInstance().getphoto(listdataBean.getAvatar(),holder.iv);


        return convertView;
    }

    private class ViewHolder{
        private TextView tv_name;
        private ImageView iv;

    }

}
