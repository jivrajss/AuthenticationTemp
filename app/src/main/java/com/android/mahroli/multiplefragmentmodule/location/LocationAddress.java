package com.android.mahroli.multiplefragmentmodule.location;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;

import com.android.mahroli.multiplefragmentmodule.service.AppLocationService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by jivraj.singh on 18-12-2015.
 */

public class LocationAddress {

    public LocationAddress(Context context) {
        this.mContext = context;
        this.appLocationService = new AppLocationService(mContext);
    }

    private final Context mContext;
    private final AppLocationService appLocationService;
    private static final String TAG = LocationAddress.class.getSimpleName();

    public static void getAddressFromLocation(final double latitude, final double longitude,
                                              final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                            sb.append(address.getAddressLine(i)).append("\n");
                        }
                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getCountryName());
                        result = sb.toString();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Unable connect to Geocoder", e);
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        Log.e(TAG, "into result not null ---getLocation()----Result" + result);
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n\nAddress:\n" + result;
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        Log.e(TAG, "into result null ---getLocation()");
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        result = "Latitude: " + latitude + " Longitude: " + longitude +
                                "\n Unable to get address for this lat-long.";
                        bundle.putString("address", result);

                        message.setData(bundle);
                    }
                    Log.d(TAG, "Result::" + result);
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }


    public void showAddress(Handler handler) {
        Location location = appLocationService
                .getLocation();
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            getAddressFromLocation(latitude, longitude,
                    mContext, handler);
        } else {
            showSettingsAlert();
        }
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                mContext);
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public void showLocation() {
        Location gpsLocation = appLocationService
                .getLocation();
        if (gpsLocation != null) {
            double latitude = gpsLocation.getLatitude();
            double longitude = gpsLocation.getLongitude();
            String result = "Latitude: " + latitude +
                    " Longitude: " + longitude;
            Log.d(TAG, result);
        } else {
            showSettingsAlert();
        }
    }

}