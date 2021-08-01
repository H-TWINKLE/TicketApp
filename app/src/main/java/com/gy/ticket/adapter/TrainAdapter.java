package com.gy.ticket.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gy.ticket.R;
import com.gy.ticket.user.Train;

import java.util.List;

/**
 * Created by TWINKLE on 2017/12/29.
 */

public class TrainAdapter extends BaseAdapter {

    List<Train> list;
    Context context;

    public TrainAdapter( List<Train> list,Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.get(0).getData().getResult().size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.content_lv_train,null);
        TextView tv_train_time = (TextView)view.findViewById(R.id.tv_train_time);
        TextView tv_train_from = (TextView)view.findViewById(R.id.tv_train_from);
        TextView tv_train_to = (TextView)view.findViewById(R.id.tv_train_to);
        TextView tv_train_ticket = (TextView)view.findViewById(R.id.tv_train_ticket);



        return view;
    }
}
