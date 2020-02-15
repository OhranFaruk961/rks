package com.example.ispit_2017_09_06.helper;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import androidx.fragment.app.FragmentActivity;


public class Util {

    public static void otvoriFragmentKaoReplace(Activity activity, int id, Fragment current_fragment, Fragment new_fragment) {
        final FragmentManager fm = (activity).getFragmentManager();
        final FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (new_fragment.isAdded()) {
            fragmentTransaction.show(new_fragment);
        } else
            fragmentTransaction.add(id, new_fragment);
        if (current_fragment != null) {
            fragmentTransaction.hide(current_fragment);
            fragmentTransaction.addToBackStack(current_fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public static void otvoriFragmentKaoDijalog(Activity activity, DialogFragment fragment) {
        final FragmentManager fm = activity.getFragmentManager();
        fragment.show(fm, "tag");
    }
}
