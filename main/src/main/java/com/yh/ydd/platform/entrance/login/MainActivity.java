package com.yh.ydd.platform.entrance.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.ydd.platfrom.R;

import lecho.lib.hellocharts.view.PreviewColumnChartView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PreviewColumnChartView previewColumnChartView = findViewById(R.id.chart);
        ColumnChartViewTools.setChartViewData(previewColumnChartView);

    }
}
