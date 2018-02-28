package com.soinve.bpc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.soinve.bpc.R;
import com.soinve.bpc.dao.DBHelper;
import com.soinve.bpc.entity.BloodPressure;

import java.util.Date;
import java.util.List;

public class AddInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText highPressEt;
    private EditText lowPressEt;
    private EditText heartRateEt;
    private Button cancleBtn;
    private Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);

        initView();
    }

    private void initView(){
        highPressEt = findViewById(R.id.high_press_et);
        lowPressEt = findViewById(R.id.low_press_et);
        heartRateEt = findViewById(R.id.heart_rate_et);
        cancleBtn = findViewById(R.id.btn_cancle);
        cancleBtn.setOnClickListener(this);
        confirmBtn = findViewById(R.id.btn_confirm);
        confirmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_cancle:
                //取消，关闭
                this.finish();
                break;
            case R.id.btn_confirm:
                saveInfo();
                break;
        }
    }

    private void saveInfo(){
        String hightPress = highPressEt.getText().toString().trim();
        String lowPress = lowPressEt.getText().toString().trim();
        String heartRate = heartRateEt.getText().toString().trim();

        if (TextUtils.isEmpty(hightPress)){
            Toast.makeText(AddInfoActivity.this, "请填写高压", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(lowPress)){
            Toast.makeText(AddInfoActivity.this, "请填写低压", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(heartRate)){
            Toast.makeText(AddInfoActivity.this, "请填写心率", Toast.LENGTH_SHORT).show();
            return;
        }

        BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setHighPressure(Integer.parseInt(hightPress));
        bloodPressure.setLowPressure(Integer.parseInt(lowPress));
        bloodPressure.setHeartRate(Integer.parseInt(heartRate));
        bloodPressure.setCreateTime(new Date());

        DBHelper.getInstance(this).insertPressInfo(bloodPressure);

        List<BloodPressure> list = DBHelper.getInstance(this).loadAll();

        System.out.print(list.size());

        this.finish();

    }
}
