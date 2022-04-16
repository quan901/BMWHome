package com.itcast.bmwhome;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RightAdapter extends BaseAdapter {
    private Context mContext;
    private List<CarBean> list;
    public RightAdapter(Context context,List<CarBean> list){
        this.mContext=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView == null){
            convertView=View.inflate(mContext,R.layout.list_item,null);
            holder=new ViewHolder();
            holder.tv_name=convertView.findViewById(R.id.tv_name);
            holder.tv_price=convertView.findViewById(R.id.tv_price);
            holder.tv_power=convertView.findViewById(R.id.tv_power);
            holder.tv_date=convertView.findViewById(R.id.tv_date);
            holder.iv_img=convertView.findViewById(R.id.iv_img);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        CarBean bean=list.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_price.setText(bean.getPrice());
        holder.tv_power.setText(bean.getPower());
        holder.tv_date.setText(bean.getDate());
        holder.iv_img.setBackgroundResource(bean.getImg());
        return convertView;
    }
    class ViewHolder{
        TextView tv_name,tv_price,tv_power,tv_date;
        ImageView iv_img;
    }
}
