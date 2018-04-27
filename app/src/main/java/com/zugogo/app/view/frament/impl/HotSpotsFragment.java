package com.zugogo.app.view.frament.impl;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zugogo.app.R;
import com.zugogo.app.view.frament.abs.AbsMenuFragment;
import com.zugogo.app.model.api.ApiManager;
import com.zugogo.app.model.api.ThreadCallback;

import static com.zugogo.app.view.activity.BasisActivity.LOADING_BAR;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotSpotsFragment extends AbsMenuFragment {
    private static final String TAG = HotSpotsFragment.class.getSimpleName();
    private static HotSpotsFragment FRAGMENT = null;

    public HotSpotsFragment() {
        // Required empty public constructor
    }

    public static AbsMenuFragment newInstance() {
        return new HotSpotsFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Object... o) {
        return new HotSpotsFragment();
    }

    @Override
    public AbsMenuFragment newInstance(Bundle bundle) {
        return new HotSpotsFragment();
    }

    public static AbsMenuFragment getInstance(Bundle bundle) {
        if (FRAGMENT == null){
            FRAGMENT = new HotSpotsFragment();
            FRAGMENT.setArguments(bundle);
        }
        return FRAGMENT;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hot_spots, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LOADING_BAR.show();
        ApiManager.test(new ThreadCallback() {
            @Override
            public void onSuccess(String responseBody) {
                Log.d(TAG, responseBody);
                LOADING_BAR.hide();
            }

            @Override
            public void onFail(Exception error) {
                Log.d(TAG, "", error);
            }
        });
    }

}
