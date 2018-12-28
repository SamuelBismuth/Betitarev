package com.example.betitarev.betitarev.helper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.betitarev.betitarev.R;
import com.example.betitarev.betitarev.activities.MainActivity;

/**
 * This class contains function to handle with fragment.
 */
public class FragmentHelper {

    /**
     * loading fragment into FrameLayout
     *
     * @param fragment
     */
    public static void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = MainActivity.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
