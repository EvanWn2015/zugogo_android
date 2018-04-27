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
public class HelpFragment extends AbsMenuFragment {
    private static final String TAG = HelpFragment.class.getSimpleName();
    private static HelpFragment FRAGMENT = null;

    public HelpFragment() {
        // Required empty public constructor
    }

    public static AbsMenuFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Object... o) {
        return new HelpFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Bundle bundle) {
        return new HelpFragment();
    }

    public static AbsMenuFragment getInstance(Bundle bundle) {
        if (FRAGMENT == null){
            FRAGMENT = new HelpFragment();
            FRAGMENT.setArguments(bundle);
        }
        return FRAGMENT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false);
    }


}
