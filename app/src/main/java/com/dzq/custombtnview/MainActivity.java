package com.dzq.custombtnview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dzq.custombtnview.utils.CommonUtil;
import com.dzq.custombtnview.widget.CustomBtnView;

public class MainActivity extends AppCompatActivity {


    CustomBtnView customBtnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    private void initUi() {
        customBtnView = (CustomBtnView) findViewById(R.id.lay_view);
        customBtnView.setCustomBtnViewOnClickListener(new CustomBtnView.CustomBtnViewOnClickListener() {
            @Override
            public void onBtnClickReturn(int position) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        String[] btnValue = {"bbbbb", "bbsfsfd", "ttttt"};
        customBtnView.initData(btnValue);

        CustomBtnView customBtnView2 = (CustomBtnView) findViewById(R.id.lay_view2);
        int viewWidthPx = (CommonUtil.getScreenWidth(this) - CommonUtil.dip2px(this, 32)) / btnValue.length;
        int viewHeightPx = CommonUtil.dip2px(this, 28);
        customBtnView2.setViewWidthPx(viewWidthPx);
        customBtnView2.setViewHeightPx(viewHeightPx);
        customBtnView2.initData(btnValue);
    }
}
