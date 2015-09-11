package me.xunhou.androiduiview.listdialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/27.
 */
public class UIListDialog implements AdapterView.OnItemClickListener {
    private Context   mContext;
    private ViewGroup decorView;
    private ViewGroup rootView;
    ViewGroup     contentContainer;
    UIListAdapter mAdapter;

    private UIListInterface mUIListOnClickListener;

    private ListView listView;
    private Button   button;

    private ArrayList<String> mItemMessage = new ArrayList<String>();

    private UIListDialog() {
    }

    public UIListDialog(Context mContext) {
        this.mContext = mContext;
        initView();
    }

    private void initView() {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_list_dialog, decorView, false);
        contentContainer = (ViewGroup) rootView.findViewById(R.id.ll_content_container);
        listView = (ListView) rootView.findViewById(R.id.lv);
        button = (Button) rootView.findViewById(R.id.btn_cancel);
        mAdapter = new UIListAdapter(mContext, mItemMessage);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUIListOnClickListener != null) {
                    mUIListOnClickListener.cancel();
                    if (isShowing()){
                        dismiss();
                    }
                }
            }
        });
    }

    private void attached(View view) {
        decorView.addView(view);
        contentContainer.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in_center));
    }

    public UIListDialog setMessages(String[] messages) {
        List<String> strings = Arrays.asList(messages);
        mItemMessage.addAll(strings);
        mAdapter.notifyDataSetChanged();
        return this;
    }

    public UIListDialog setMessages(List<String> messages) {
        mItemMessage.clear();
        mItemMessage.addAll(messages);
        mAdapter.notifyDataSetChanged();
        return this;
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

    public void setOnClickListener(UIListInterface uiListInterface) {
        mUIListOnClickListener = uiListInterface;
    }

    public void build() {
        if (isShowing()) {
            return;
        }
        attached(rootView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (mUIListOnClickListener != null) {
            TextView textView = (TextView) view.findViewById(R.id.tv_message);
            mUIListOnClickListener.select(textView.getText().toString().trim());
            if (isShowing()){
                dismiss();
            }
        }
    }
}
