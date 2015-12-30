package com.times.ussd.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by umesh on 27/11/15.
 */
public class FragmentHelper {

    /**
     * Replaces fragment without adding it to the back stack .
     */
    public static void replaceFragment(FragmentActivity activity, Fragment fragment,
                                       int containerId) {
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().replace(containerId, fragment).commitAllowingStateLoss();
    }


}

