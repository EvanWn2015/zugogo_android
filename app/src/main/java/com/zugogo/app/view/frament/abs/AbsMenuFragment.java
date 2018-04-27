package com.zugogo.app.view.frament.abs;

import android.app.Fragment;
import android.os.Bundle;

import com.zugogo.app.view.frament.intf.MenuFragment;

/**
 * Created by evan on 2018/1/12.
 */

public abstract class AbsMenuFragment extends Fragment implements MenuFragment {
    private static final String TAG = AbsMenuFragment.class.getSimpleName();

    @Override
    public MenuFragment newInstance(Bundle bundle) {
        return null;
    }

    @Override
    public MenuFragment newInstance(Object... o) {
        return null;
    }

}
