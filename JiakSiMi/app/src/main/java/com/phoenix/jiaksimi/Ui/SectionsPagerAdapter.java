package com.phoenix.jiaksimi.Ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.phoenix.jiaksimi.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position) {
            case 1:
                return Menu.newInstance();
            case 2:
                return Adder.newInstance();
            default:
                return Randomizer.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.rand_tab);
            case 1:
                return mContext.getResources().getString(R.string.menu_tab);
            case 2:
                return mContext.getResources().getString(R.string.adder_tab);
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        // 3 pages total.
        return 3;
    }
}