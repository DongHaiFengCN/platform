/*
package com.yh.ydd.platform.entrance.register;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ydd.platform.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    */
/**
     * 平台界面的信息
     *//*

    private String email;

    private String companyName;

    private String password;


    */
/**
     * 电话号码
     *//*

    private String telephone;

    */
/**
     * 选择身份
     *//*

    private int[] role;





    private ImageView imageView;

    private int[] navigation;

    private int p = 0;

    private Button next;

    private Button last;

    private PlatformFragment platformFragment;


    private RoleFragment roleFragment;

    private CompleteFragment completeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initNavigationIcon();

        initView();

        platformFragment = new PlatformFragment();

        getFragmentManager().beginTransaction().replace(R.id.fragment_container, platformFragment).commit();

        next.setOnClickListener(v -> nextUI());

        last.setOnClickListener(v -> lastUI());

    }

    private void initView() {

        imageView = findViewById(R.id.navigation_header_container);

        imageView.setBackground(getResources().getDrawable(navigation[p]));

        next = findViewById(R.id.next);
        last = findViewById(R.id.last);

        last.setVisibility(View.INVISIBLE);


    }

    private void initNavigationIcon() {
        navigation = new int[4];
        navigation[0] = R.drawable.reg_nav_platform;
        navigation[1] = R.drawable.reg_nav_validation;
        navigation[2] = R.drawable.reg_nav_role;
        navigation[3] = R.drawable.reg_nav_complete;
    }

    */
/**
     * 验证注册信息入口
     *
     * @return boolean
     *//*

    private boolean verify() {

        //标记当前界面是否校验通过
        boolean flag = true;

        if (p == 0) {

            flag = platform();

            if (flag) {

                //加载下一个界面

                if (telephoneFragment == null) {

                    telephoneFragment = new TelephoneFragment();
                }

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, telephoneFragment).commit();


            }


        } else if (p == 1) {


            flag = telephone();

            if (flag) {

                //加载下一个界面

                if (roleFragment == null) {

                    roleFragment = new RoleFragment();
                }

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, roleFragment).commit();


            }


        } else if (p == 2) {

            flag = role();

            if (flag) {

                //加载下一个界面

                if (completeFragment == null) {

                    completeFragment = new CompleteFragment();
                }

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, completeFragment).commit();


            }


        } else if (p == 3) {

            finish();
        }


        return flag;
    }

    */
/**
     * 验证用户名，密码，邮箱
     * @return boolean
     *//*

    private boolean platform() {




        return true;
    }

    */
/**
     * 验证手机号是否验证通过
     * @return
     *//*

    private boolean telephone() {

        return true;
    }

    private boolean role() {

        return true;
    }

    */
/**
     * 下一步按钮事件
     *//*

    private void nextUI() {


        if (!verify()) {

            return;
        }


        if (p < 3) {

            last.setVisibility(View.VISIBLE);

            p++;

            imageView.setBackground(getResources().getDrawable(navigation[p]));

            if (p == 3) {

                next.setText("提交");
            }


        }


    }

    */
/**
     * 回退按钮事件
     *//*

    private void lastUI() {


        if (p > 0) {

            if (p == 3) {

                next.setText("下一步");
            } else if (p == 1) {

                last.setVisibility(View.INVISIBLE);
            }

            p--;

            imageView.setBackground(getResources().getDrawable(navigation[p]));

            if (p == 2) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, roleFragment).commit();

            } else if (p == 1) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, telephoneFragment).commit();

            } else if (p == 0) {

                getFragmentManager().beginTransaction().replace(R.id.fragment_container, platformFragment).commit();

            }


        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
*/
