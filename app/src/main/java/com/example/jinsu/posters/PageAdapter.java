package com.example.jinsu.posters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jinsu on 2018-03-25.
 */

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new PayFragment();
            case 2:
                return new BarFragment();
            case 3:
                return new BonusFragment();
            case 4:
                return new ListFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
