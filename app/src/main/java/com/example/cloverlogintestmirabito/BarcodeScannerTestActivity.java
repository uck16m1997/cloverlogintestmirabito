package com.example.cloverlogintestmirabito;


import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.clover.sdk.v1.Intents;
import com.clover.sdk.v3.scanner.BarcodeResult;
import com.clover.sdk.v3.scanner.BarcodeScanner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BarcodeScannerTestActivity extends Activity {
    private static final String TAG = "BarcodeScannerTestActivity";

    private Account mAccount;
    private BarcodeScanner mBarcodeScanner;

    private CheckBox qrCode;
    private CheckBox code1D;
    private TextView resultTV;
    private Spinner avail;
    private CheckBox sendFacing;

    private BroadcastReceiver barcodeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BarcodeResult barcodeResult = new BarcodeResult(intent);

            if (barcodeResult.isBarcodeAction()) {
                String barcode = barcodeResult.getBarcode();
                resultTV.setText(barcode);
                MockApi mock=new MockApi();

                if(mock.card_check(barcode)){
                    Intent intent3 =new Intent(getApplicationContext(), Main2Activity.class);
                    intent3.putExtra("barcode",barcode);
                    startActivity(intent3);
                }
                else{
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Invalid Card");
                    builder1.setCancelable(false);

                    builder1.setPositiveButton(
                            "okay",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });



                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carcodetest);

        qrCode = (CheckBox) findViewById(R.id.checkBoxQRCode);
        code1D = (CheckBox) findViewById(R.id.checkBox1DCode);

        resultTV = (TextView) findViewById(R.id.resultTV);
        avail = findViewById(R.id.avail);

        sendFacing = (CheckBox) findViewById(R.id.send_facing);
        sendFacing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    avail.setEnabled(true);
                } else {
                    avail.setEnabled(false);
                }
           }
        });
        sendFacing.setChecked(false);

        mBarcodeScanner = new BarcodeScanner(this);

        Button start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = new Bundle();
                extras.putBoolean(Intents.EXTRA_LED_ON, false);
                extras.putBoolean(Intents.EXTRA_SCAN_QR_CODE, qrCode.isChecked());
                extras.putBoolean(Intents.EXTRA_SCAN_1D_CODE, code1D.isChecked());
                if (sendFacing.isChecked()) {
                    extras.putInt(Intents.EXTRA_SCANNER_FACING, getScannerFacing());
                }
                mBarcodeScanner.executeStartScan(extras);

                resultTV.setText("");
            }
        });

        Button stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBarcodeScanner.executeStopScan(null);
            }
        });

    }

    private int getScannerFacing() {
        FacingItem fi = (FacingItem) avail.getSelectedItem();
        return fi.facing;
    }

    private static class FacingItem {
        final int facing;

        private FacingItem(int facing) {
            this.facing = facing;
        }

        @Override
        public String toString() {
            return BARCODE_SCANNER_FACING_TOSTRING.get(facing);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerBarcodeScanner();

        new AsyncTask<Void,Void,List<Integer>>() {
            @Override
            protected List<Integer> doInBackground(Void... voids) {
                return mBarcodeScanner.getAvailable();
            }

            @Override
            protected void onPostExecute(List<Integer> facings) {
                ArrayAdapter<FacingItem> adapter =
                        new ArrayAdapter<>(getApplicationContext(), R.layout.item_adapter_scanners, toBarcodeScannerFacings(facings));
                avail.setAdapter(adapter);
            }
        }.execute();
    }

    private static Map<Integer,String> BARCODE_SCANNER_FACING_TOSTRING = new HashMap<Integer,String>() {{
        put(BarcodeScanner.BARCODE_SCANNER_FACING_DUAL, "Dual-facing (either customer or merchant facing)");
        put(BarcodeScanner.BARCODE_SCANNER_FACING_MERCHANT, "Merchant-facing");
        put(BarcodeScanner.BARCODE_SCANNER_FACING_CUSTOMER, "Customer-facing");
    }};

    private static List<FacingItem> toBarcodeScannerFacings(Collection<Integer> facings) {
        List<FacingItem> items = new ArrayList<>();
        for (Integer f: facings) {
            items.add(new FacingItem(f));
        }
        return items;
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterBarcodeScanner();
    }

    private void registerBarcodeScanner() {
        registerReceiver(barcodeReceiver, new IntentFilter(BarcodeResult.INTENT_ACTION));
    }

    private void unregisterBarcodeScanner() {
        unregisterReceiver(barcodeReceiver);
    }
}