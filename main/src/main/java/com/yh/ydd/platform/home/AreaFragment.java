package com.yh.ydd.platform.home;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ydd.platfrom.R;
import com.yh.ydd.common.basedo.AreaSearchImp;
import com.yh.ydd.common.view.SearchComponent;

public class AreaFragment extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_home_area_fg,null);
        SearchComponent searchComponent = view.findViewById(R.id.search_cmp);

        //设置搜索模式
        searchComponent.setSearchModel(new AreaSearchImp());

        //添加搜索结果返回监听
        searchComponent.addSearchListener(hashMapList -> {

        });



        return view;
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            //TODO 如果是二次显示的话需要刷新一下界面数据就可以在这显示了

        }
    }
}
