package com.example.mvpclean.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.mvpclean.R;
import com.example.mvpclean.ui.contents.ContentsFragment;
import com.example.mvpclean.ui.custom.BottomNavigationHelper;
import com.example.mvpclean.ui.empty.BlankFragment;
import com.example.mvpclean.ui.home.HomeFragment;
import com.example.mvpclean.utils.ActivityUtils;
import com.example.mvpclean.utils.HistoryUtil;
import com.example.mvpclean.utils.InternetUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener, ContentsFragment.OnFragmentInteractionListener {

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
        InternetUtil.offlineCheck(navigation,this);

    }
    private void showSnackBar(String msg){
        Snackbar.make(findViewById(R.id.container), msg, Snackbar.LENGTH_SHORT).show();
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
            case R.id.navigation_upload:
            case R.id.navigation_noti:
            case R.id.navigation_profile:
                goEmptyFragment();
                return true;
        }
        return false;
    }

    @Override
    public void goHomeFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), HomeFragment.newInstance(), R.id.container, false);
    }

    @Override
    public void goEmptyFragment() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), BlankFragment.newInstance(), R.id.container, false);

    }

    @Override
    public void onFragmentInteractionMsg(String msg) {
        showSnackBar(msg);
    }
}
