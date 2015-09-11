package me.xunhou.androiduiview.listdialog;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/31.
 */
public class UIListAdapter extends BaseAdapter {

    private ArrayList<String> messages;
    private Context           mContext;

    public UIListAdapter(Context context, ArrayList<String> contents) {
        this.messages = contents;
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
    public View getView(int position, View converView, ViewGroup viewGroup) {

        String message = messages.get(position);
        View view = null;
        if (converView == null) {
            view = View.inflate(mContext, R.layout.item_list_dialog, null);
        } else {
            view = converView;
        }

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.ll);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        if (position == 0) {
            ll.setBackgroundResource(R.drawable.corner_top_selector);
        } else {
            ll.setBackgroundResource(R.drawable.corner_selector);
        }
        tvMessage.setText(message + "");

        return view;
    }

}
