package com.android.mahroli.multiplefragmentmodule;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jivraj.singh on 17-12-2015.
 */
public class test extends AppCompatActivity implements Top_Fragment.textEntered,View.OnClickListener {
    String value;
//    boolean check=false;
    Bottom_Fragment frg2;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView one,two,three;
        one = (TextView) findViewById(R.id.FirstTextView);
        two = (TextView) findViewById(R.id.SecondTextView);
        three= (TextView)findViewById(R.id.ThirdTextView);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        Top_Fragment frg=new Top_Fragment();
        frg2=new Bottom_Fragment();
        FragmentManager manager=getSupportFragmentManager();
        transaction=manager.beginTransaction();
        transaction.add(R.id.My_Container_1_ID, frg, "Frag_Top_tag");
        transaction.add(R.id.My_Container_3_ID, frg2, "Frag_Bottom_tag");
        transaction.commit();
    }

    @Override
    public void setValue(String editextvalue) {
        // TODO Auto-generated method stub
        value =editextvalue;
        Log.i("..............",""+value);
        if (frg2 != null) {
            frg2.setName(value);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"fragment 2  is null", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FirstTextView:
                if(findViewById(R.id.My_Container_1_ID).getVisibility()==View.VISIBLE)
                    findViewById(R.id.My_Container_1_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_1_ID).setVisibility(View.VISIBLE);
                break;
            case R.id.SecondTextView:
                if(findViewById(R.id.My_Container_2_ID).getVisibility()==View.VISIBLE)
                    findViewById(R.id.My_Container_2_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_2_ID).setVisibility(View.VISIBLE);
                break;
            case R.id.ThirdTextView:
                if(findViewById(R.id.My_Container_3_ID).getVisibility()==View.VISIBLE)
                    findViewById(R.id.My_Container_3_ID).setVisibility(View.GONE);
                else
                    findViewById(R.id.My_Container_3_ID).setVisibility(View.VISIBLE);
                break;

        }
    }
}



