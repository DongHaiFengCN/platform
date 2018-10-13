package com.yh.ydd.platform.main;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ydd.platform.R;
import com.yh.ydd.common.basedo.AreaSearchImp;
import com.yh.ydd.common.untils.Utils;
import com.yh.ydd.common.view.SearchComponent;
import java.util.HashMap;
import java.util.List;

public class SFRestaurantFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_restaurant_fg, null);

        ListView areaLv = view.findViewById(R.id.area_lv);

        areaLv.setAdapter(new MyAdapter());

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) areaLv.getLayoutParams();
        layoutParams.setMargins(0,0,0,Utils.getNavigationBarHeight(getActivity()));
        areaLv.setLayoutParams(layoutParams);

        SearchComponent searchComponent = view.findViewById(R.id.search_cmp);
        //设置搜索模式
        searchComponent.setSearchModel(new AreaSearchImp());
        //添加搜索结果返回监听
        searchComponent.addSearchListener(new SearchComponent.SearchResponseListener() {
            @Override
            public void searchListener(List<HashMap> hashMapList) {
                //返回搜索结果
                Log.e("DOAING", hashMapList.get(0).get("name").toString());
            }

            @Override
            public void cancel() {
                //取消按钮

                Log.e("DOAING", "取消");

            }
        });


        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //TODO 如果是二次显示的话需要刷新一下界面数据就可以在这显示了
            Log.e("DOAING","hidden");

        }
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;

            if (convertView == null) {

                convertView = getActivity().getLayoutInflater().inflate(R.layout.home_restaurant_fg_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText("山东省");
            viewHolder.telephone.setText("17605413611");
            viewHolder.address.setText("山东省济南市xxxxxxxxxxxxxxxxxxxxxxxxxx");
            viewHolder.leader.setText("董海峰");

            return convertView;
        }

        class ViewHolder {

            TextView name;
            TextView telephone;
            TextView address;
            TextView leader;

            ViewHolder(View view) {

                name = view.findViewById(R.id.name_tv);
                telephone = view.findViewById(R.id.telephone_tv);
                address = view.findViewById(R.id.address_tv);
                leader = view.findViewById(R.id.leader_tv);

            }
        }

    }


}
