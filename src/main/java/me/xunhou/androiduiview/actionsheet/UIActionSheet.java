package me.xunhou.androiduiview.actionsheet;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/31.
 */
public class UIActionSheet {

    private Context   mContext;
    private ViewGroup decorView;
    private ViewGroup rootView;
    ViewGroup contentContainer;

    private UIActionSheet() {
    }

    public UIActionSheet(Context mContext) {
        this.mContext = mContext;
        initView();
    }


    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_action_sheet, decorView, false);
        contentContainer = (ViewGroup) rootView.findViewById(R.id.ll_content_container);
        ListView listView = (ListView) rootView.findViewById(R.id.lv);

        ArrayList<String> arrayList = new ArrayList<String>();
        UIActionSheetAdapter uiActionSheetAdapter = new UIActionSheetAdapter(mContext,arrayList);
        listView.setAdapter(uiActionSheetAdapter);
    }


    private void attached(View view) {
        decorView.addView(view);
        contentContainer.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in_center));
    }

    public boolean isShowing() {
        View view = decorView.findViewById(R.id.ll_content_container);
        return view != null;
    }

    public void dismiss() {
        if (isShowing()) {
            dismiss(rootView);
        }
    }

    private void dismiss(View view) {
        decorView.removeView(view);
        contentContainer.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_out_center));
    }

    public void build() {
        if (isShowing()) {
            return;
        }
        attached(rootView);
    }


}
