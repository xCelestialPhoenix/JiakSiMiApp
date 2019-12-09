package com.phoenix.jiaksimi.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.phoenix.jiaksimi.Adder;
import com.phoenix.jiaksimi.Menu;
import com.phoenix.jiaksimi.R;
import com.phoenix.jiaksimi.Randomizer;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = Randomizer.newInstance();
                break;
            case 1:
                fragment = Menu.newInstance();
                break;
            case 2:
                fragment = Adder.newInstance();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = mContext.getResources().getString(R.string.rand_tab);
                break;
            case 1:
                title = mContext.getResources().getString(R.string.menu_tab);
                break;
            case 2:
                title = mContext.getResources().getString(R.string.adder_tab);
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        // 3 pages total.
        return 3;
    }
}