package me.xunhou.androiduiview.listdialog;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/27.
 */
public class UIListDialog extends Dialog {

    private ListView listView;

    private View          header;
    private UIListAdapter uiListAdapter;

    private List<String> messages = new ArrayList<>();

    public UIListDialog(Context context) {
        super(context);
        initView();
    }

    public UIListDialog(Context context, int theme) {
        super(context, theme);
        initView();
    }

    protected UIListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    private void initView() {
        setContentView(R.layout.layout_list_dialog);
        listView = (ListView) findViewById(R.id.lv);
        uiListAdapter = new UIListAdapter(getContext(), messages);
    }

    public void setTitle(String title) {
        if (header != null) {
            listView.removeHeaderView(header);
        }
        header = LayoutInflater.from(getContext()).inflate(R.layout.header_list_dialog, null);
        TextView tvTitle = (TextView) header.findViewById(R.id.tv_title);
        tvTitle.setText(title);
        listView.addHeaderView(header);
    }

    public void setMessages(List<String> msgs) {
        messages.clear();
        messages.addAll(msgs);
        uiListAdapter.notifyDataSetChanged();
    }

    public void show() {
        super.show();
    }

}
