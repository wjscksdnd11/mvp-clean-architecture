package com.example.tranbesample.utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityUtils {

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId, boolean isBackStack) {

        try {
            checkNotNull(fragmentManager);
            checkNotNull(fragment);
            if(!fragmentManager.isDestroyed()) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(frameId, fragment);
                if(isBackStack) transaction.addToBackStack(null);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
