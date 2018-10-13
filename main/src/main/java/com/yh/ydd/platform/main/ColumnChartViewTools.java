package com.yh.ydd.platform.main;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;

public class ColumnChartViewTools {

    public static void setChartViewData(ColumnChartView chart) {

        //底部标题
        List<String> title = new ArrayList<>();
        //颜色值
        List<Integer> color = new ArrayList<>();
        //X、Y轴值list
        List<AxisValue> axisXValues = new ArrayList<>();


        //所有的
        List<Column> columns = new ArrayList<>();


        title.add("1月");
        title.add("2月");
        title.add("3月");
        title.add("4月");
        title.add("5月");
        title.add("6月");
        title.add("7月");
        title.add("8月");
        title.add("9月");
        title.add("10月");
        title.add("11月");
        title.add("12月");


        //关键设置每个x轴坐标的数据
        for (int i = 0; i < title.size(); i++) {

            //柱子容器
            Column column = new Column();

            //柱子至少有一个
            List<SubcolumnValue> mPointValues = new ArrayList<>();

            //设置柱子的高度以及柱子的颜色
            mPointValues.add(new SubcolumnValue((float) (100 * Math.random()), Color.parseColor("#009BDB")));

            //设置X轴的柱子所对应的属性名称(底部文字)
            axisXValues.add(new AxisValue(i).setLabel(title.get(i)));


            //是否显示每个柱子的标签
            column.setHasLabels(true);

            //设置每个柱子的Lable是否选中，为false，表示不用选中，一直显示在柱子上
            column.setHasLabelsOnlyForSelected(false);

            //这里柱子绑定一个list 可以设置多个子柱子
            column.setValues(mPointValues);

            //绑定到x轴的坐标上
            columns.add(column);
        }



        //设置Columns添加到Data中
        ColumnChartData columnData = new ColumnChartData(columns);

        //底部
        Axis axisBottom = new Axis(axisXValues);
        //是否显示X轴的网格线
        axisBottom.setHasLines(false);
        //分割线颜色
        axisBottom.setLineColor(Color.parseColor("#ff0000"));
        //字体颜色
        axisBottom.setTextColor(Color.parseColor("#666666"));
        //字体大小
        axisBottom.setTextSize(15);
        //底部文字
      //  axisBottom.setName("底部标题");
        //每个柱子的便签是否倾斜着显示
        axisBottom.setHasTiltedLabels(false);
        //距离各标签之间的距离,包括离Y轴间距 (0-32之间)
        axisBottom.setMaxLabelChars(2);
        //设置是否自动生成轴对象,自动适应表格的范围(设置之后底部标题变成0-5)
        //axisBottom.setAutoGenerated(true);
        axisBottom.setHasSeparationLine(true);
        //设置x轴在底部显示
        columnData.setAxisXBottom(axisBottom);

        //左边  属性与上面一致
        Axis axisLeft = new Axis();
        axisLeft.setHasLines(false);
      //  axisLeft.setName("左边标题");
        axisLeft.setHasTiltedLabels(true);
        axisLeft.setTextColor(Color.parseColor("#666666"));
        columnData.setAxisYLeft(axisLeft);

        //设置数据标签的字体大小
        //data.setValueLabelTextSize(12);
        //设置数据标签的字体颜色
        //data.setValueLabelsTextColor(Color.WHITE);
        //设置数据背景是否跟随节点颜色
        //data.setValueLabelBackgroundAuto(true);
        //设置是否有数据背景  是否跟随columvalue的颜色变化
        // data.setValueLabelBackgroundEnabled(true);
        //设置坐标点旁边的文字背景(...)
        //data.setValueLabelBackgroundColor(Color.YELLOW);
        //axisBottom.setMaxLabelChars(5);
        //设置组与组之间的间隔比率,取值范围0-1,1表示组与组之间不留任何间隔
        columnData.setFillRatio(0.5f);
        chart.setInteractive(false);
        //最后将所有值显示在View中
        chart.setColumnChartData(columnData);
    }
}