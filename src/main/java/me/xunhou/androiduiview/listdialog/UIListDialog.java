package me.xunhou.androiduiview.listdialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/27.
 */
public class UIListDialog {

    private Context   mContext;
    private ViewGroup decorView;
    private ViewGroup rootView;
    ViewGroup contentContainer;

    private ListView listView;

    private View          header;
    private UIListAdapter uiListAdapter;

    private List<String> messages = new ArrayList<>();

    public UIListDialog(Context context) {
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_list_dialog, decorView, false);
        contentContainer = (ViewGroup) rootView.findViewById(R.id.ll_content_container);
        listView = (ListView) rootView.findViewById(R.id.lv);
        uiListAdapter = new UIListAdapter(mContext, messages);
        listView.setAdapter(uiListAdapter);
    }

    public void setTitle(String title) {
//        if (header != null) {
//            listView.removeHeaderView(header);
//        }
//        header = LayoutInflater.from(mContext).inflate(R.layout.header_list_dialog, null);
//        TextView tvTitle = (TextView) header.findViewById(R.id.tv_title);
//        tvTitle.setText(title);
//        listView.addHeaderView(header);
    }

    public void setMessages(List<String> msgs) {
        messages.clear();
        messages.addAll(msgs);
        uiListAdapter.notifyDataSetChanged();
    }

    private void show(View view) {
        decorView.addView(view);
        contentContainer.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in_center));
    }

    public void build(){
        show(rootView);
    }

}
