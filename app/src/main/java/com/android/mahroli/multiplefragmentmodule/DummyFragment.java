package com.android.mahroli.multiplefragmentmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.appyvet.rangebar.RangeBar;

/**
 * Created by jivraj.singh on 17-12-2015.
 */
public class DummyFragment extends Fragment implements RangeBar.OnRangeBarChangeListener {
    Context mContext;
    private final static String TAG = DummyFragment.class.getSimpleName();
    private FrameLayout baseLayout;
    private View placeHolderView;
    private ActionBar mActionBar;
    private RangeBar rangebar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.dummy_layout,
                container, false);
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        rangebar = (RangeBar) rootView.findViewById(R.id.rangebar);
        rangebar.setOnRangeBarChangeListener(this);
        return rootView;
    }


    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
        Log.d(TAG,"LeftPinIndex--"+leftPinIndex+"--rightPinIndex--"+rightPinIndex);
        Log.d(TAG,"LeftPinValue--"+leftPinValue+"--rightPinValue--"+rightPinValue);
    }
}