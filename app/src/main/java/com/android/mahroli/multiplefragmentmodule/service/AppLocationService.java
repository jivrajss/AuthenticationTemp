package com.android.mahroli.multiplefragmentmodule.service;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.mahroli.multiplefragmentmodule.location.LocationAddress;

/**
 * Created by jivraj.singh on 18-12-2015.
 */
public class AppLocationService extends Service implements LocationListener {

    protected LocationManager locationManager;
    Location location;

    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 1000 * 60 * 2;

    public AppLocationService(Context context) {
        locationManager = (LocationManager) context
                .getSystemService(LOCATION_SERVICE);
    }

//    public Location getLocation(String provider) {
//        if (locationManager.isProviderEnabled(provider)) {
//            try {
//                locationManager.requestLocationUpdates(provider,
//                        MIN_TIME_FOR_UPDATE, MIN_DISTANCE_FOR_UPDATE, this);
//                if (locationManager != null) {
//                    location = locationManager.getLastKnownLocation(provider);
//                    return location;
//                }
//            } catch (SecurityException ex) {
//                ex.printStackTrace();
//                Log.e(LocationAddress.class.getSimpleName(), "Exception Occurred @ " + ex.getMessage());
//            }
//        }
//        return null;
//    }

    public Location getLocation() throws SecurityException{
        Location loc=null;
        Criteria criteria = new Criteria();
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >=
                android.os.Build.VERSION_CODES.HONEYCOMB) {
            criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);
            criteria.setAccuracy(Criteria.ACCURACY_FINE);
            criteria.setAltitudeRequired(true);
            criteria.setBearingRequired(true);
            criteria.setSpeedRequired(true);
        }
        String provider = locationManager.getBestProvider(criteria,
                true);
        loc = locationManager.getLastKnownLocation(provider);
        if (loc == null) {
            boolean gps_enabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            boolean network_enabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (gps_enabled) {
                loc = locationManager
                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }
            if (network_enabled && loc == null) {
                loc = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
        }
        return loc;
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

}
