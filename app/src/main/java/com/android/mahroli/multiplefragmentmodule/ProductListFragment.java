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
import android.widget.TextView;

/**
 * Created by praveen on 04.09.15.
 */
public class ProductListFragment extends Fragment implements View.OnClickListener {
    static final String TAG = ProductListFragment.class.getSimpleName();
    Context mContext;

    private FrameLayout baseLayout;
    private View placeHolderView;
    private ActionBar mActionBar;

    private TextView one,two,three;
    View oneFragment,twoFragment,threeFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        View rootView = inflater.inflate(R.layout.fragment_product_list,
                container, false);

        mActionBar= ((AppCompatActivity)getActivity()).getSupportActionBar();
        one = (TextView) rootView.findViewById(R.id.FirstTextView);
        two = (TextView) rootView.findViewById(R.id.SecondTextView);
        three= (TextView) rootView.findViewById(R.id.ThirdTextView);
        oneFragment = rootView.findViewById(R.id.article_fragment);
        twoFragment = rootView.findViewById(R.id.article_fragment2);
        threeFragment = rootView.findViewById(R.id.article_fragment3);


        return rootView;
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
                if(oneFragment.getVisibility()==View.VISIBLE)
                    oneFragment.setVisibility(View.GONE);
                else
                oneFragment.setVisibility(View.VISIBLE);
                break;
            case R.id.SecondTextView:
                if(twoFragment.getVisibility()==View.VISIBLE)
                    twoFragment.setVisibility(View.GONE);
                else
                    twoFragment.setVisibility(View.VISIBLE);
            break;
            case R.id.ThirdTextView:
                if(threeFragment.getVisibility()==View.VISIBLE)
                    threeFragment.setVisibility(View.GONE);
                else
                    threeFragment.setVisibility(View.VISIBLE);
                break;

        }
    }
}
