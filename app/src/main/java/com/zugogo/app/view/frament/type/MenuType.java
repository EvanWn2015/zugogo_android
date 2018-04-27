package com.zugogo.app.view.frament.type;

import com.zugogo.app.R;
import com.zugogo.app.view.frament.abs.AbsMenuFragment;
import com.zugogo.app.view.frament.impl.AttractionsFragment;
import com.zugogo.app.view.frament.impl.EWalletFragment;
import com.zugogo.app.view.frament.impl.HelpFragment;
import com.zugogo.app.view.frament.impl.HomeFragment;
import com.zugogo.app.view.frament.impl.HotSpotsFragment;
import com.zugogo.app.view.frament.impl.MarketingActivitiesFragment;
import com.zugogo.app.view.frament.impl.MemberManagementFragment;
import com.zugogo.app.view.frament.impl.RentalFragment;
import com.zugogo.app.view.frament.impl.RideRecordFragment;
import com.zugogo.app.view.frament.impl.VipFragment;

/**
 * Created by evan on 2018/1/7.
 */

public enum MenuType {

    HOME(R.id.nav_home, HomeFragment.class),
    HOT_SPOTS(R.id.nav_hot_spots, HotSpotsFragment.class),
    RENTAL(R.id.nav_rental, RentalFragment.class),
    E_WALLET(R.id.nav_ride_e_wallet, EWalletFragment.class),
    ATTRACTIONS(R.id.nav_attractions, AttractionsFragment.class),
    RIDE_RECORD(R.id.nav_ride_record, RideRecordFragment.class),
    VIP(R.id.nav_vip, VipFragment.class),
    MARKETING_ACTIVITIES(R.id.nav_marketing_activities, MarketingActivitiesFragment.class),
    MEMBER_MANAGEMENT(R.id.nav_member_management, MemberManagementFragment.class),
    HELP(R.id.nav_help, HelpFragment.class);


    private final int id;
    private final Class zlass;

    MenuType(int id, Class zlass) {
        this.id = id;
        this.zlass = zlass;
    }

    public boolean equals(int id) {
        return this.id == id;
    }

    public static MenuType ofId(int id) {
        for (MenuType entity : values()) {
            if (entity.equals(id)) {
                return entity;
            }
        }
        return null;
    }

    public Class toClass() {
        return this.zlass.asSubclass(AbsMenuFragment.class);
    }

}
