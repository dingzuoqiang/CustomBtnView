package com.dzq.custombtnview.widget;


import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dzq.custombtnview.R;
import com.dzq.custombtnview.utils.CommonUtil;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;


/**
 * 自定义控件  按钮
 */
public class CustomBtnView extends LinearLayout {

    private int leftBtnBgResId = R.drawable.bg_custom_btn_view_left_selector;
    private int rightBtnBgResId = R.drawable.bg_custom_btn_view_right_selector;
    private int middleBtnBgResId = R.drawable.bg_custom_btn_view_middle_selector;
    private float textSize = 14;
    private int textColor = R.color.text_656565_white_selector;
    private int viewWidthPx;
    private int viewHeightPx;
    private List<TextView> viewList;


    private CustomBtnViewOnClickListener customBtnViewOnClickListener = null;
    private int position;

    public CustomBtnView(Context context) {
        this(context, null);
    }

    public CustomBtnView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        viewWidthPx = CommonUtil.dip2px(getContext(), 70);
        viewHeightPx = CommonUtil.dip2px(getContext(), 24);
    }

    public void initData(final String[] btnValue) {

        if (btnValue == null || btnValue.length == 0) return;
        viewList = new ArrayList<>();
        removeAllViewsInLayout();
        int size = btnValue.length;
        for (int i = 0; i < size; i++) {
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(btnValue[i]);
            textView.setTag(i);
            textView.setTextSize(textSize);

            try {
                XmlPullParser xrp = getResources().getXml(textColor);
                ColorStateList csl = ColorStateList.createFromXml(getResources(), xrp);
                textView.setTextColor(csl);
            } catch (Exception e) {
                textView.setTextColor(getContext().getResources().getColor(textColor));
            }
            if (size == 1)
                textView.setBackgroundResource(middleBtnBgResId);
            else {
                if (i == 0) {
                    textView.setBackgroundResource(leftBtnBgResId);
                } else if (i == (size - 1)) {
                    textView.setBackgroundResource(rightBtnBgResId);
                } else {
                    textView.setBackgroundResource(middleBtnBgResId);
                }
            }
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    position = (int) view.getTag();
                    btnClick(position);
                    if (customBtnViewOnClickListener != null)
                        customBtnViewOnClickListener.onBtnClickReturn(position);
                }
            });
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.width = viewWidthPx;
            layoutParams.height = viewHeightPx;
            addView(textView, layoutParams);
            viewList.add(textView);

        }


    }

    private void btnClick(int position) {
        if (viewList != null && position < viewList.size()) {
            for (int i = 0; i < viewList.size(); i++) {
                viewList.get(i).setSelected(position == i);
            }
        }

    }

    public void setLeftBtnBgResId(int leftBtnBgResId) {
        this.leftBtnBgResId = leftBtnBgResId;
    }

    public void setRightBtnBgResId(int rightBtnBgResId) {
        this.rightBtnBgResId = rightBtnBgResId;
    }

    public void setMiddleBtnBgResId(int middleBtnBgResId) {
        this.middleBtnBgResId = middleBtnBgResId;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setViewHeightPx(int viewHeightPx) {
        this.viewHeightPx = viewHeightPx;
    }

    public void setViewWidthPx(int viewWidthPx) {
        this.viewWidthPx = viewWidthPx;
    }

    public void setCustomBtnViewOnClickListener(CustomBtnViewOnClickListener customBtnViewOnClickListener) {
        this.customBtnViewOnClickListener = customBtnViewOnClickListener;
    }

    public interface CustomBtnViewOnClickListener {
        public void onBtnClickReturn(int position);//
    }

}