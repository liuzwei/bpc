package com.soinve.bpc.controller;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.soinve.bpc.R;
import com.soinve.bpc.dao.DBHelper;
import com.soinve.bpc.entity.BloodPressure;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewCureActivity extends AppCompatActivity {

    private LineChart bloodChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cure);

        initView();
        initData();
    }

    private void initView(){
        bloodChart = findViewById(R.id.blood_chart);
        bloodChart.setTouchEnabled(true);
    }

    private void initData(){
        List<Entry> highEntries = new ArrayList<>();
        List<Entry> lowEntries = new ArrayList<>();
        List<Entry> heartEntries = new ArrayList<>();

        List<BloodPressure> list = DBHelper.getInstance(ViewCureActivity.this).loadAll();
        for (BloodPressure bl : list){
            highEntries.add(new Entry(bl.getCreateTime().getTime(), bl.getHighPressure()));
            lowEntries.add(new Entry(bl.getCreateTime().getTime(), bl.getLowPressure()));
            heartEntries.add(new Entry(bl.getCreateTime().getTime(), bl.getHeartRate()));
        }
        LineDataSet highDataSet = new LineDataSet(highEntries, "高压");
        highDataSet.setColor(Color.BLUE);
        highDataSet.setValueTextColor(Color.BLUE);
        highDataSet.setValueTextSize(9f);
        highDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });

        LineDataSet lowDataSet = new LineDataSet(lowEntries, "低压");
        lowDataSet.setColor(Color.GREEN);
        lowDataSet.setValueTextColor(Color.GREEN);
        lowDataSet.setValueTextSize(9f);
        lowDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });

        LineDataSet heartDataSet = new LineDataSet(heartEntries, "心率");
        heartDataSet.setColor(Color.RED);
        heartDataSet.setValueTextColor(Color.RED);
        heartDataSet.setValueTextSize(9f);
        heartDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int IValue = (int) value;
                return String.valueOf(IValue);
            }
        });


        IAxisValueFormatter xFormatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                long v = (long)value;
                Date date = new Date(v);
                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
                return sdf.format(date);
            }
        };

        List<ILineDataSet> lineDataSetList = new ArrayList<>();
        lineDataSetList.add(highDataSet);
        lineDataSetList.add(lowDataSet);
        lineDataSetList.add(heartDataSet);

        LineData lineData = new LineData(lineDataSetList);
        XAxis xAxis = bloodChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(xFormatter);

        //设置横轴描述
        Description description = new Description();
        description.setText("时间");
        description.setTextColor(Color.RED);
        bloodChart.setDescription(description);

        bloodChart.setData(lineData);
        bloodChart.invalidate();
    }

}
