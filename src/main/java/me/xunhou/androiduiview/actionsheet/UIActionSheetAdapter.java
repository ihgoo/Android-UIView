package me.xunhou.androiduiview.actionsheet;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/9/11.
 */
public class UIActionSheetAdapter extends BaseAdapter {

    private ArrayList<String> contents;
    private Context           mContext;

    public UIActionSheetAdapter(Context context, ArrayList<String> contents) {
        this.contents = contents;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 5;
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
    public View getView(int i, View converView, ViewGroup viewGroup) {
        View view = null;
        if (converView == null) {
            view = View.inflate(mContext, R.layout.item_action_sheet, null);
        } else {
            view = converView;
        }
        return view;
    }
}
