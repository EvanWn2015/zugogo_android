package com.zugogo.app.view.frament.intf;

import android.os.Bundle;

/**
 * Created by evan on 2018/1/7.
 */

public interface MenuFragment {

    MenuFragment newInstance(Bundle bundle);

    MenuFragment newInstance(Object... o);
//        MenuFragment newInstance();
}
