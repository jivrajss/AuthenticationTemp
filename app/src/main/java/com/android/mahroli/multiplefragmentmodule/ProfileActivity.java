package com.android.mahroli.multiplefragmentmodule;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.mahroli.multiplefragmentmodule.location.LocationAddress;
import com.android.mahroli.multiplefragmentmodule.profile.BasicInfo;

/**
 * Created by jivraj.singh on 17-12-2015.
 */
public class ProfileActivity extends AppCompatActivity implements Top_Fragment.textEntered, View.OnClickListener {
    String value;
    //    boolean check=false;
    Bottom_Fragment frg2;
    FragmentTransaction transaction;
    private LocationAddress mAddress;
    private final static String TAG = ProfileActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAddress = new LocationAddress(ProfileActivity.this);
        mAddress.showLocation();
        mAddress.showAddress(new GeocoderHandler());
        findViewById(R.id.TabLayout).setOnClickListener(this);
        findViewById(R.id.TabLayout2).setOnClickListener(this);
        findViewById(R.id.TabLayout3).setOnClickListener(this);
        BasicInfo frg = new BasicInfo();
        frg2 = new Bottom_Fragment();

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.My_Container_1_ID, new BasicInfo(), "Frag_Top_tag");
        transaction.add(R.id.My_Container_2_ID, new BasicInfo(), "Frag_middle_tag");
        transaction.add(R.id.My_Container_3_ID, new DummyFragment(), "Frag_Bottom_tag");
        transaction.commit();
    }

    @Override
    public void setValue(String editextvalue) {
        // TODO Auto-generated method stub
        value = editextvalue;
        Log.i("..............", "" + value);
        if (frg2 != null) {
            frg2.setName(value);
        } else {
            Toast.makeText(getApplicationContext(), "fragment 2  is null", Toast.LENGTH_LONG).show();
        }
    }

    public static class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            Log.d(TAG, "Handler---" + locationAddress);

        }
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        switch (v.getId()) {
            case R.id.TabLayout:
                Toast.makeText(this, "One", Toast.LENGTH_SHORT).show();
                if (findViewById(R.id.My_Container_1_ID).getVisibility() == View.VISIBLE)
                    findViewById(R.id.My_Container_1_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_1_ID).setVisibility(View.VISIBLE);
                break;
            case R.id.TabLayout2:
                Toast.makeText(this, "Two", Toast.LENGTH_SHORT).show();
                if (findViewById(R.id.My_Container_2_ID).getVisibility() == View.VISIBLE)
                    findViewById(R.id.My_Container_2_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_2_ID).setVisibility(View.VISIBLE);
                break;
            case R.id.TabLayout3:
                Toast.makeText(this, "Three", Toast.LENGTH_SHORT).show();
                if (findViewById(R.id.My_Container_3_ID).getVisibility() == View.VISIBLE)
                    findViewById(R.id.My_Container_3_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_3_ID).setVisibility(View.VISIBLE);
                break;

        }
    }
}



