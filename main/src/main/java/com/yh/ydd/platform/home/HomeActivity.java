package com.yh.ydd.platform.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ydd.platfrom.R;


public class HomeActivity extends AppCompatActivity {

    private ListView itemLv;

    private int height;

    private int[] imageNormal ={R.mipmap.ic_home_normal,R.mipmap.ic_china_map_normal,R.mipmap.ic_action_normal,R.mipmap.ic_shop_normal,R.mipmap.ic_user_normal,R.mipmap.ic_storage_normal};
    private int[] imagePress ={R.mipmap.ic_home_press,R.mipmap.ic_china_map_press,R.mipmap.ic_action_press,R.mipmap.ic_shop_press,R.mipmap.ic_user_press,R.mipmap.ic_storage_press};
    private String[] title = {"主页","区域管理","营销","门店管理","权限","仓库"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleMarginTop(getStatusBarHeight());
        toolbar.setNavigationIcon(R.drawable.ic_facebook);
        setSupportActionBar(toolbar);

        itemLv = findViewById(R.id.list_item);
        LinearLayout linearLayout = findViewById(R.id.line1);

        ViewTreeObserver viewTreeObserver = linearLayout.getViewTreeObserver();

        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                MyAdapter myAdapter = new MyAdapter();
                linearLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                height = linearLayout.getHeight() / title.length;
                ViewGroup.LayoutParams layoutParams = itemLv.getLayoutParams();
                layoutParams.width = height;
                itemLv.setLayoutParams(layoutParams);
                itemLv.setAdapter(myAdapter);
                itemLv.setOnItemClickListener((parent, view, position, id) -> myAdapter.selectPosition(position));

            }
        });


    }

    /**
     * 获取系统状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private class MyAdapter extends BaseAdapter {

        private int position = -1;
        @Override
        public int getCount() {
            return title.length;
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

            ViewHolder viewHolder = null;

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.activity_home_navigation_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(title[position]);

            if(this.position == position){

                viewHolder.icon.setImageResource(imagePress[position]);

                viewHolder.navigationVi.setVisibility(View.VISIBLE);

                viewHolder.name.setTextColor(getResources().getColor(R.color.colorPrimary));

            }else {

                viewHolder.icon.setImageResource(imageNormal[position]);

                viewHolder.navigationVi.setVisibility(View.INVISIBLE);

                viewHolder.name.setTextColor(getResources().getColor(R.color.textViewColorNormal));

            }
            return convertView;
        }

        class ViewHolder {

            TextView name;
            ImageView icon;
            View navigationVi;


            ViewHolder(View view) {

                navigationVi = view.findViewById(R.id.navigation_vi);

                icon = view.findViewById(R.id.imageView);

                name =view.findViewById(R.id.title_tv);

                //设置蓝色导航条高度
               setItemHeight(navigationVi);

               //设置内容区的高度
               setItemHeight(view.findViewById(R.id.line2));

            }
            private void setItemHeight(View view) {

                ViewGroup.LayoutParams layoutParams1 = view.getLayoutParams();
                layoutParams1.height = height;
                view.setLayoutParams(layoutParams1);
            }



        }

        public void selectPosition(int position){

            this.position = position;
            notifyDataSetChanged();

        }
    }

}
