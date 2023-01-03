package com.example.myflowers;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVER_BT = 1;

    TextView mStatusBlueTv, mPairedTv;
    ImageView mBlueIv;
    Button mOnBtn, mOffBtn, mDiscoverBtn, mPairedBtn, mScanBtn;

    ListView scanListView;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter mBlueAdapter;


    //
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Log.e("Activity result", "OK");
                    // There are no request codes
                    Intent data = result.getData();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        mStatusBlueTv   = findViewById(R.id.statusBluetoothTv);
        mPairedBtn      = findViewById(R.id.pairedBtn);
        mBlueIv         = findViewById(R.id.bluetoothIv);
        mOffBtn         = findViewById(R.id.offBtn);
        mOnBtn          = findViewById(R.id.onBtn);
        mPairedTv       = findViewById(R.id.pairedTv);
        mDiscoverBtn    = findViewById(R.id.discoverableBtn);
        mScanBtn        = findViewById(R.id.nearByBtn);
        //  scanListView    = findViewById(R.id.nearByLv);


        //adapter
        mBlueAdapter = BluetoothAdapter.getDefaultAdapter();

        //check if bluetoot is available or not
        if (mBlueAdapter == null) {
            mStatusBlueTv.setText("Bluetooth is not available");
            mBlueIv.setImageResource(R.drawable.ic_action_off);
        } else {
            mStatusBlueTv.setText("Bluetooth is available");
        }

        // Set image according to bluetooth
        if (mBlueAdapter.isEnabled()) {
            mBlueIv.setImageResource(R.drawable.ic_action_on);
        } else {
            mBlueIv.setImageResource(R.drawable.ic_action_off);
        }

        // on btn click
        mOnBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!mBlueAdapter.isEnabled()) {
                    showToast("Turning on Bluetooth..");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    activityResultLauncher.launch(intent);
                    mBlueIv.setImageResource(R.drawable.ic_action_on);
                    mStatusBlueTv.setText("Bluetooth on");
                } else {
                    showToast("Bluetooth is already on");
                }
            }
        });

        // off btn click
        mOffBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if (mBlueAdapter.isEnabled()) {
                    // Supress exeption
                    mBlueAdapter.disable();
                    showToast("Turning Bluetooth Off");
                    mBlueIv.setImageResource(R.drawable.ic_action_off);
                    mStatusBlueTv.setText("Bluetooth off");
                } else {
                    showToast("Bluetooth is already off");
                }
            }
        });

        // Discover bluetooth
        mDiscoverBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {

                // Tästä pupression permisson
                if (!mBlueAdapter.isDiscovering()) {
                    showToast("Making your device Discoverable");
                    mBlueIv.setImageResource(R.drawable.ic_action_on);
                    mStatusBlueTv.setText("Bluetooth on");
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    activityResultLauncher.launch(intent);
                }
            }
        });

        //Scan nearby available devices
        mScanBtn.setOnClickListener(new View.OnClickListener() {

            // ETSINTÄ AIKA TAHAn
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if(mBlueAdapter.isEnabled()){
                    mPairedTv.setText("");
                    showToast("Scanning nearby devices..");
                    // ETSINTÄ AIKA TAHAn
                    mBlueAdapter.startDiscovery();


                    mBlueIv.setImageResource(R.drawable.ic_action_searching);

                }
                else {
                    showToast("Turn bluetooth on to scan devices");
                }

            }

        });

        // TÄssä pitäisi olla alkuperäisesti
        // IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        // registerReceiver(myReceiver,intentFilter);
        // arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.test_list_item,stringArrayList);
        // scanListView.setAdapter(arrayAdapter);

        // get paired devices btn click
        mPairedBtn.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                if (mBlueAdapter.isEnabled()) {
                    mPairedTv.setText("Paired Devices");
                    Set<BluetoothDevice> devices = mBlueAdapter.getBondedDevices();
                    for (BluetoothDevice device: devices){
                        mPairedTv.append("\nDevice: " + device.getName() + ", " + device);
                    }
                }
                else {
                    // Bluetoot is of so cant get paired devices
                    showToast("Turn on bluetooth to get paired devices");
                }


            }
        });

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Functiot
    //Broadcastreceiver
    BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @SuppressLint("MissingPermission")
        @Override
        public void onReceive(Context context, Intent intent) {
            showToast("On receiving..");
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    };

    // DISCOVER KOKEILU
    private void registerForActivityResult(Intent intent, int requestDiscoverBt) {
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if (resultCode==RESULT_OK){
                    //Bluetooth is on
                    mBlueIv.setImageResource(R.drawable.ic_action_on);
                    showToast("Bluetooth is on");
                }
                else {
                    showToast("Could't on bluetooth");
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    // toast message function
    private void showToast (String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();

    }

}
