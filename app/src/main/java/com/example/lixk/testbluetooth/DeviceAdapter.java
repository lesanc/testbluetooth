package com.example.lixk.testbluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {
    private HashMap<String, BluetoothDevice> mDeviceMap;

    public DeviceAdapter(Context context, HashMap<String, BluetoothDevice> deviceMap){
        mDeviceMap = deviceMap;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_item, parent,false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {
        String[] list = mDeviceMap.keySet().toArray(new String[0]);
        holder.mTvDeviceName.setText(list[position]);
    }

    @Override
    public int getItemCount() {
        return mDeviceMap.size();
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView mTvDeviceName;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            mTvDeviceName = itemView.findViewById(R.id.device_name);
        }
    }
}
