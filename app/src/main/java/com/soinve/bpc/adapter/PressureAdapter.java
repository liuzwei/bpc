package com.soinve.bpc.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.soinve.bpc.R;
import com.soinve.bpc.entity.BloodPressure;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by liuzhaowei on 2018/2/27.
 */

public class PressureAdapter extends RecyclerView.Adapter<PressureAdapter.ViewHolder> {

    //数据
    private List<BloodPressure> mData;

    public PressureAdapter(List<BloodPressure> mData){
        this.mData = mData;
    }

    public void updateData(List<BloodPressure> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        BloodPressure bloodPressure = mData.get(position);
        holder.hightPressTv.setText(bloodPressure.getHighPressure().toString());
        holder.lowPressTv.setText(bloodPressure.getLowPressure().toString());
        holder.hearRateTv.setText(bloodPressure.getHeartRate().toString());

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");

        holder.timeTv.setText(sdf.format(bloodPressure.getCreateTime()));

    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView hightPressTv;
        TextView lowPressTv;
        TextView hearRateTv;
        TextView timeTv;

        public ViewHolder(View itemView) {
            super(itemView);
            hightPressTv = (TextView) itemView.findViewById(R.id.high_press_tv);
            lowPressTv = (TextView) itemView.findViewById(R.id.low_press_tv);
            hearRateTv = (TextView) itemView.findViewById(R.id.heart_rate_tv);
            timeTv = (TextView) itemView.findViewById(R.id.create_time_tv);
        }
    }
}
