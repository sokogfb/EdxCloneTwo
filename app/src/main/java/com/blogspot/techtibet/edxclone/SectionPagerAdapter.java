package com.blogspot.techtibet.edxclone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter{
    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                CourseFragment courseFragment=new CourseFragment();
                return  courseFragment;
            case 1:
                DiscoveryFragment discoveryFragment=new DiscoveryFragment();
                return  discoveryFragment;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Courses";
            case 1:
                return "Discovery";
                default:
                    return null;
        }
    }
}
