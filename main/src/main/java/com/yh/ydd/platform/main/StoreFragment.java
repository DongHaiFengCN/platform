package com.yh.ydd.platform.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ydd.platform.R;

public class StoreFragment extends Fragment {

    private SFRestaurantFragment SFRestaurantFragment;


    private View view;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:

                selectFragment(0);

                return true;
            case R.id.navigation_dashboard:

                selectFragment(1);

                return true;
            case R.id.navigation_notifications:

                selectFragment(1);
                return true;

        }
        return false;
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_main_store_fg, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        selectFragment(0);

    }

    /**
     * 打开指定位置的fragment
     *
     * @param position
     */
    @SuppressLint("NewApi")
    private void selectFragment(int position) {
        // 开启一个Fragment事务

        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager
                .beginTransaction();


        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (position) {
            case 0:
                if (SFRestaurantFragment == null) {
                    SFRestaurantFragment = new SFRestaurantFragment();
                    transaction.add(R.id.container, SFRestaurantFragment);
                } else {
                    transaction.show(SFRestaurantFragment);
                }
                break;
            case 1:
               /* if (placeholderFragment == null) {
                    placeholderFragment = new PlaceholderFragment();
                    transaction.add(R.id.container, placeholderFragment);
                } else {
                    transaction.show(placeholderFragment);
                }*/
                break;
            default:
                break;
        }
        transaction.commit();

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (SFRestaurantFragment != null) {
            transaction.hide(SFRestaurantFragment);
        }
        if (SFRestaurantFragment != null) {
            transaction.hide(SFRestaurantFragment);
        }
    }







}
