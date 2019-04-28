package com.example.tranbesample.ui.custom_view;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Stack;

public class BottomStackNavigation extends BottomNavigationView {
    Stack<Integer> bottomNavigation = new Stack<>();
    public BottomStackNavigation(Context context) {
        super(context);
    }

    public BottomStackNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomStackNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public boolean isEmptybottomStack(){
        return bottomNavigation.empty();
    }
    public void popStack(){
        setSelectedItemId(bottomNavigation.pop());
    }

    @Override
    public int getSelectedItemId() {
        int currentItemId = super.getSelectedItemId();
        if (bottomNavigation.peek()!=currentItemId)
            bottomNavigation.push(currentItemId);
        return currentItemId;

    }
}
