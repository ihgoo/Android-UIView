/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package me.xunhou.androiduiview.listdialog;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by ihgoo on 2015/8/31.
 */
public class UIListAdapter extends BaseAdapter {

    private List<String> messages;
    private Context      mContext;

    public UIListAdapter(Context context, List<String> messages) {
        this.messages = messages;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }

}
