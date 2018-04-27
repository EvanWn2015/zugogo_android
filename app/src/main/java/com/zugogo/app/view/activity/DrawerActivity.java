package com.zugogo.app.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.zugogo.app.R;
import com.zugogo.app.view.frament.abs.AbsMenuFragment;
import com.zugogo.app.view.frament.factory.MenuFragmentFactory;
import com.zugogo.app.view.frament.intf.MenuFragment;
import com.zugogo.app.view.frament.type.MenuType;

public class DrawerActivity extends BasisActivity implements NavigationView.OnNavigationItemSelectedListener {

    //    private FrameLayout mainFrame;
    private static final String TAG = DrawerActivity.class.getSimpleName();
    private FragmentManager fragmentManager;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private ImageView toolbarLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        toolbar = findViewById(R.id.toolbar);
        toolbarLogo = findViewById(R.id.im_toolbar_logo);
        setSupportActionBar(toolbar);

        // write
        getEditor().edit()
                .putString(getString(R.string.shared_gcm_token), "gcm token test")
                .commit();
        // read
        String gcm = getRead().getString(getString(R.string.shared_gcm_token), "");

        fragmentManager = getFragmentManager();

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
//        drawer.closeDrawer(GravityCompat.START);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN, GravityCompat.START);
//        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
//        drawer.closeDrawer(GravityCompat.START);

        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        // VIP item color
        setItemTextColor(navigationView.getMenu().findItem(R.id.nav_vip), R.color.menu_item_vip_font_default);
        setItemIconColor(navigationView.getMenu().findItem(R.id.nav_vip), R.color.menu_item_vip_font_default);

        navigationView.setCheckedItem(R.id.nav_home);
        setItemIconColor(navigationView.getMenu().findItem(R.id.nav_home), R.color.menu_item_icon_activated);
        setTag(navigationView.getMenu().findItem(R.id.nav_home));
        MenuFragment fragment = MenuFragmentFactory.of(MenuType.HOME, null);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, (Fragment) fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test_t, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        setTag(item);
        setToolBarStyle(item);
        if (id == R.id.nav_vip) {
            setItemIconColor(item, R.color.menu_item_vip_font_activated);
            setItemTextColor(item, R.color.menu_item_vip_font_activated);
        } else {
            setItemIconColor(item, R.color.menu_item_icon_activated);
        }
        if (id == R.id.nav_logout) {
            finish();
            return true;
        }
        AbsMenuFragment fragment = MenuFragmentFactory.of(MenuType.ofId(id), null);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame_layout, fragment);
        fragmentTransaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private MenuItem tmpItem = null;

    private void setTag(MenuItem item) {
        if (tmpItem != null) {
            setItemIconColor(tmpItem, tmpItem.getItemId() != R.id.nav_vip ? R.color.menu_item_icon_default : R.color.menu_item_vip_font_default);
            setItemTextColor(tmpItem, tmpItem.getItemId() != R.id.nav_vip ? R.color.menu_item_font_default : R.color.menu_item_vip_font_default);
        }
        tmpItem = item;
    }

    private void setItemTextColor(MenuItem item, int colorResource) {
        SpannableString s = new SpannableString(item.getTitle());
        s.setSpan(new ForegroundColorSpan(getColor(colorResource)), 0, s.length(), 0);
        item.setTitle(s);
    }

    private void setItemIconColor(MenuItem item, int colorResource) {
        Drawable oldItem = item.getIcon();
        oldItem.setTint(getColor(colorResource));
        item.setIcon(oldItem);
    }

    private void setToolBarStyle(MenuItem item) {

        int id = item.getItemId();
//        if (item.getItemId() == R.id.nav_home) {
//            toolbar.setBackgroundResource(R.drawable.toolbar_background);
//
//            toolbarLogo.setVisibility(View.VISIBLE);
//        } else {
//            toolbar.setBackgroundResource(R.drawable.toolbar_background_default);
//            toolbarLogo.setVisibility(View.GONE);
//            toolbar.setTitle(item.getTitle());
//        }

        toolbar.setBackgroundResource(id == R.id.nav_home ? R.drawable.toolbar_background : R.drawable.toolbar_background_default);
        toolbarLogo.setVisibility(id == R.id.nav_home ? View.VISIBLE : View.GONE);
        toolbar.setTitle(id == R.id.nav_home ? null : item.getTitle());
    }
}
