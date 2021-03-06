package com.soinve.bpc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.soinve.bpc.adapter.PressureAdapter;
import com.soinve.bpc.controller.AddInfoActivity;
import com.soinve.bpc.controller.ViewCureActivity;
import com.soinve.bpc.dao.DBHelper;
import com.soinve.bpc.entity.BloodPressure;
import com.soinve.bpc.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, OnItemClickListener{

    private RecyclerView recyclerView;
    private PressureAdapter pressureAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static List<BloodPressure> dataInfo = new ArrayList<>();

    private static Integer page = 1;
    private static Integer pageSize = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddInfoActivity.class);
                startActivity(intent);
            }
        });

        initData();
        initView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        dataInfo.addAll(getData());
        pressureAdapter = new PressureAdapter(dataInfo, this);
    }

    private void initView(){
        recyclerView  =findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(pressureAdapter);

        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                refreshlayout.finishRefresh(500/*,false*/);//传入false表示刷新失败
                page = 1;
                dataInfo.clear();
                dataInfo.addAll(getData());
                pressureAdapter.updateData(dataInfo);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(500/*,false*/);//传入false表示加载失败
                page++;
                dataInfo.addAll(getData());
                pressureAdapter.updateData(dataInfo);
            }
        });
    }

    private List<BloodPressure> getData() {
        return DBHelper.getInstance(MainActivity.this).pageSelectInfo(page, pageSize);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.view_cure://曲线查看
                Intent intent = new Intent(MainActivity.this,ViewCureActivity.class);
                startActivity(intent);
                break;
            case R.id.measure_remind:
                Toast.makeText(MainActivity.this, "开发中，敬请期待！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.feedback:
                Toast.makeText(MainActivity.this, "请联系QQ:522034228", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    @Override
    public void onItemLongClick(View view, final int position) {
        new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定删除?")
                .setContentText("删除后不可恢复!")
                .setConfirmText("是,确认删除!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        BloodPressure bloodPressure = dataInfo.remove(position);
                        //删除本地数据库数据
                        DBHelper.getInstance(MainActivity.this).deletePressInfo(bloodPressure);
                        //刷新页面Adatper
                        pressureAdapter.updateData(dataInfo);

                        sDialog.dismissWithAnimation();

                    }
                })
                .show();
    }
}
