package com.example.lixk.testbluetooth;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";
    private Button mBtnScan, mBtnShortVibrate, mBtnLongVibrate, mBtnNoStop, mBtnStop;
    private TextView mTvSteps, mTvPower, mTvConnectState;
    private BluetoothAdapter mBluetoothAdapter;
    private HashMap<String, BluetoothDevice> mDeviceMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        BluetoothManager bluetoothManager = (BluetoothManager)getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        
        if (!mBluetoothAdapter.isEnabled()){
            openBlueTooth();
        }

        mDeviceMap.put("ok1", null);
        mDeviceMap.put("ok2", null);
        mDeviceMap.put("ok3", null);
        mDeviceMap.put("ok4", null);
        
        RecyclerView recyclerView = findViewById(R.id.device_list);
        DeviceAdapter deviceAdapter = new DeviceAdapter(this, mDeviceMap);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(deviceAdapter);
    }

    private void initView() {
        mBtnScan = findViewById(R.id.scan);
        mBtnShortVibrate = findViewById(R.id.short_vibrate);
        mBtnLongVibrate = findViewById(R.id.long_vibrate);
        mBtnNoStop = findViewById(R.id.no_stop);
        mBtnStop = findViewById(R.id.stop);

        mTvSteps = findViewById(R.id.steps);
        mTvPower = findViewById(R.id.power);
        mTvConnectState = findViewById(R.id.connect_state);

        mBtnScan.setOnClickListener(this);
        mBtnShortVibrate.setOnClickListener(this);
        mBtnLongVibrate.setOnClickListener(this);
        mBtnNoStop.setOnClickListener(this);
        mBtnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scan:
                scan();
                break;
            case R.id.short_vibrate:
                break;
            case R.id.long_vibrate:
                break;
            case R.id.no_stop:
                break;
            case R.id.stop:
                break;
            default:
                break;
        }
    }

    private void openBlueTooth(){
        Log.d(TAG, "openBlueTooth: ");
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, 0);
        Toast.makeText(this, "蓝牙已开启", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("NewApi")
    private void scan(){
        if (!mBluetoothAdapter.isEnabled()){
            openBlueTooth();
        }

        mDeviceMap.clear();
        BluetoothLeScanner scanner = mBluetoothAdapter.getBluetoothLeScanner();
        Log.d(TAG, "scan: ");
        scanner.startScan(new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
               
                BluetoothDevice device = result.getDevice();
                if (mDeviceMap.get(device.getName()) == null){
                    mDeviceMap.put(device.getName(), device);
                    Log.d(TAG, "onScanResult: name = " + " " + device.getName() + " " + device.getAddress());
                }

                Log.d(TAG, "onScanResult: " + callbackType + " " + mDeviceMap.size());
            }
            
            
        });
    }
}
