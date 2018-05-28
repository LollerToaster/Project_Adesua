package com.project.adrianangub.project_adesua;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//REFERENCE
//http://www.truiton.com/2015/06/android-tabs-example-fragments-viewpager/

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                VirtualClassroomOneFragment tab1 = new VirtualClassroomOneFragment();
                return tab1;
            case 1:
                VirtualClassroomTwoFragment tab2 = new VirtualClassroomTwoFragment();
                return tab2;
            case 2:
                VirtualClassroomThreeFragment tab3 = new VirtualClassroomThreeFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}