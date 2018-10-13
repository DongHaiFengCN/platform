package com.yh.ydd.platform.main;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ydd.platform.R;

import lecho.lib.hellocharts.view.ColumnChartView;

public class HomeFragment extends Fragment {

    private View view;

    private TextView day;
    private TextView month;
    private TextView year;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_main_home_fg, null);

        CardView dayCd = view.findViewById(R.id.day_cd);
        day = view.findViewById(R.id.day_tv);
        dayCd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                //cardView.setCardBackgroundColor(getActivity().getColor(R.color.black_overlay));
                //  textView.setBackgroundResource(R.color.card);
                //  textView.setTextColor(getActivity().getColor(R.color.white));

                selectDate(0);

            }
        });

        CardView monthCd = view.findViewById(R.id.month_cd);
        month = view.findViewById(R.id.month_tv);
        monthCd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                selectDate(1);
            }
        });
        CardView yearCd = view.findViewById(R.id.year_cd);
        year = view.findViewById(R.id.year_tv);
        yearCd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                selectDate(2);
            }
        });

        //默认选中当天
        dayCd.performClick();

        ColumnChartView previewColumnChartView = view.findViewById(R.id.chart);

        ColumnChartViewTools.setChartViewData(previewColumnChartView);

        ListView shop = view.findViewById(R.id.shop_lv);
        View view1 = getLayoutInflater().inflate(R.layout.home_fg_item, null);
        shop.addHeaderView(view1);
        view1.setBackgroundColor(getActivity().getColor(R.color.split));
        shop.setAdapter(new MyAdapter());
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //TODO 如果是二次显示的话需要刷新一下界面数据就可以在这显示了

            // Log.e("DOAING","hidden");

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void selectDate(int i) {

        switch (i) {

            case 0:

                day.setBackgroundResource(R.color.card);
                day.setTextColor(getActivity().getColor(R.color.white));

                month.setBackgroundResource(R.color.white);
                month.setTextColor(getActivity().getColor(R.color.textColor));

                year.setBackgroundResource(R.color.white);
                year.setTextColor(getActivity().getColor(R.color.textColor));

                break;
            case 1:

                day.setBackgroundResource(R.color.white);
                day.setTextColor(getActivity().getColor(R.color.textColor));

                month.setBackgroundResource(R.color.card);
                month.setTextColor(getActivity().getColor(R.color.white));

                year.setBackgroundResource(R.color.white);
                year.setTextColor(getActivity().getColor(R.color.textColor));


                break;
            case 2:

                day.setBackgroundResource(R.color.white);
                day.setTextColor(getActivity().getColor(R.color.textColor));

                month.setBackgroundResource(R.color.white);
                month.setTextColor(getActivity().getColor(R.color.textColor));

                year.setBackgroundResource(R.color.card);
                year.setTextColor(getActivity().getColor(R.color.white));

                break;
            default:
                break;
        }

    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 5;
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
            View view = getLayoutInflater().inflate(R.layout.home_fg_item, null);

            TextView name = view.findViewById(R.id.name_tv);

            name.setText("大明湖店");

            TextView amount = view.findViewById(R.id.amount_tv);

            amount.setText("1234677");
            return view;
        }
    }
}
