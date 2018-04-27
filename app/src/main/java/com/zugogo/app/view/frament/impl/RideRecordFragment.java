package com.zugogo.app.view.frament.impl;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zugogo.app.R;
import com.zugogo.app.view.frament.abs.AbsMenuFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RideRecordFragment extends AbsMenuFragment {
    private static final String TAG = RideRecordFragment.class.getSimpleName();
    private static RideRecordFragment FRAGMENT = null;

    public RideRecordFragment() {
        // Required empty public constructor
    }

    public static AbsMenuFragment newInstance() {
        return new RideRecordFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Object... o) {
        return new RideRecordFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Bundle bundle) {
        return new RideRecordFragment();
    }

    public static AbsMenuFragment getInstance(Bundle bundle) {
        if (FRAGMENT == null){
            FRAGMENT = new RideRecordFragment();
            FRAGMENT.setArguments(bundle);
        }
        return FRAGMENT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ride_record, container, false);
    }

}
