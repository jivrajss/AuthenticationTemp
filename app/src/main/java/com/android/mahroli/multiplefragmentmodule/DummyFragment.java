package com.android.mahroli.multiplefragmentmodule;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by jivraj.singh on 17-12-2015.
 */
public class DummyFragment extends Fragment {
    static final String TAG = ProductListFragment.class.getSimpleName();
    Context mContext;

    private FrameLayout baseLayout;
    private View placeHolderView;
    private ActionBar mActionBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.dummy_layout,
                container, false);
        mActionBar= ((AppCompatActivity)getActivity()).getSupportActionBar();

        return rootView;
    }





}