package com.example.tranbesample.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.tranbesample.R;
import com.example.tranbesample.ui.custom.BottomNavigationHelper;
import com.example.tranbesample.ui.home.HomeFragment;
import com.example.tranbesample.utils.ActivityUtils;
import com.example.tranbesample.utils.HistoryUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    HistoryUtil historyUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        BottomNavigationHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        historyUtil = new HistoryUtil(R.id.navigation_home);
        navigation.setSelectedItemId(R.id.navigation_home);

    }

    @Override
    public void onBackPressed() {
        if (historyUtil.isNoHistory()|| navigation.getSelectedItemId()== historyUtil.getInitId())
            super.onBackPressed();
        else
            navigation.setSelectedItemId(historyUtil.getPreviousId());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        historyUtil.addCurrentHistory(menuItem.getItemId());
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                goHomeFragment();
                return true;
            case R.id.navigation_shopping:
                return true;
            case R.id.navigation_upload:
                return true;
            case R.id.navigation_noti:
                return true;
            case R.id.navigation_profile:
                return true;
        }
        return false;
    }

    @Override
    public void goHomeFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.container, false);
    }
}
