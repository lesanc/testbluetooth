package com.example.lixk.testbluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    private List<BluetoothDevice> mDeviceList;
    private View.OnClickListener mListener;

    public DeviceAdapter(Context context, List<BluetoothDevice> deviceList){
        mDeviceList = deviceList;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_item, parent,false);
        view.setOnClickListener(mListener);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.mTvDeviceName.setText(mDeviceList.get(position).getName());
        holder.mTvDeviceAddress.setText(mDeviceList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

    public void setOnItemClickListener(final OnItemClickListener listener){
        mListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                listener.onClick(v, mDeviceList.get(pos));
            }
        };
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDeviceName;
        TextView mTvDeviceAddress;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            mTvDeviceName = itemView.findViewById(R.id.device_name);
            mTvDeviceAddress = itemView.findViewById(R.id.device_address);
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, BluetoothDevice device);
    }
}
