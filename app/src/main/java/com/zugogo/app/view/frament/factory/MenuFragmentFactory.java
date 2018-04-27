package com.zugogo.app.view.frament.factory;

import android.os.Bundle;
import android.util.Log;

import com.zugogo.app.view.frament.abs.AbsMenuFragment;
import com.zugogo.app.view.frament.type.MenuType;

/**
 * Created by evan on 2018/1/7.
 */

public class MenuFragmentFactory {

    private final static String TAG = MenuFragmentFactory.class.getSimpleName();

    public static AbsMenuFragment of(MenuType menuType, Bundle bundle)  {

        try{
            Class<? extends AbsMenuFragment> zlass = menuType.toClass();
            AbsMenuFragment fragment = (AbsMenuFragment) zlass.newInstance().newInstance(bundle);
            Log.d(TAG, zlass.getName());
            return fragment;
        }catch (IllegalAccessException | InstantiationException e){
            Log.e(TAG, e.getMessage());
            return null;
        }

    }
}
