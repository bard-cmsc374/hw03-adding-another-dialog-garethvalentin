package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by cs374 on 11/1/16.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}