package com.yh.ydd.platform.home;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ydd.platfrom.R;
import com.yh.ydd.common.untils.Utils;


public class HomeActivity extends AppCompatActivity {

    private ListView itemLv;

    private int height;
    private MainFragment mainFragment;

    private AreaFragment areaFragment;
    private int[] imageNormal = {R.mipmap.ic_home_normal, R.mipmap.ic_china_map_normal, R.mipmap.ic_action_normal, R.mipmap.ic_shop_normal, R.mipmap.ic_user_normal, R.mipmap.ic_storage_normal};
    private int[] imagePress = {R.mipmap.ic_home_press, R.mipmap.ic_china_map_press, R.mipmap.ic_action_press, R.mipmap.ic_shop_press, R.mipmap.ic_user_press, R.mipmap.ic_storage_press};
    private String[] title = {"主页", "区域管理", "营销", "门店管理", "权限", "仓库"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

    }

    /**
     * 初始化视图
     */
    private void initView() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleMarginTop(Utils.getStatusBarHeight(getApplicationContext()));
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

                //解决系统虚拟按键遮挡的部分，只使用不遮挡的部分党作导航的
                height = (linearLayout.getHeight() - Utils.getNavigationBarHeight(getApplicationContext())) / title.length;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) itemLv.getLayoutParams();
                layoutParams.width = height;
                itemLv.setLayoutParams(layoutParams);
                itemLv.setAdapter(myAdapter);
                itemLv.setOnItemClickListener((parent, view, position, id) -> {

                    myAdapter.selectPosition(position);

                    selectFragment(position);

                });

            }
        });
    }

    /**
     * 打开指定位置的fragment
     * @param position
     */
    @SuppressLint("NewApi")
    private void selectFragment(int position) {
        // 开启一个Fragment事务

        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager
                .beginTransaction();


        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);

        switch (position) {
            case 0:
                if (mainFragment == null) {
                    mainFragment = new MainFragment();
                    transaction.add(R.id.fragment_container, mainFragment);
                } else {
                    transaction.show(mainFragment);
                }
                break;
            case 1:
                if (areaFragment == null) {
                    areaFragment = new AreaFragment();
                    transaction.add(R.id.fragment_container, areaFragment);
                } else {
                    transaction.show(areaFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();

        }
    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (mainFragment != null) {
            transaction.hide(mainFragment);
        }
        if (areaFragment != null) {
            transaction.hide(areaFragment);
        }
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

            ViewHolder viewHolder;

            if (convertView == null) {

                convertView = getLayoutInflater().inflate(R.layout.activity_home_navigation_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);

            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(title[position]);

            if (this.position == position) {

                viewHolder.icon.setImageResource(imagePress[position]);

                viewHolder.navigationVi.setVisibility(View.VISIBLE);

                viewHolder.name.setTextColor(getResources().getColor(R.color.colorPrimary));

            } else {

                viewHolder.icon.setImageResource(imageNormal[position]);

                viewHolder.navigationVi.setVisibility(View.INVISIBLE);

                viewHolder.name.setTextColor(getResources().getColor(R.color.textColor));

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

                name = view.findViewById(R.id.title_tv);

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

        public void selectPosition(int position) {

            this.position = position;
            notifyDataSetChanged();

        }
    }

}
