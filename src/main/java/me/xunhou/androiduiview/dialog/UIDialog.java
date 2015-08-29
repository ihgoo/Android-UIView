package me.xunhou.androiduiview.dialog;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import me.xunhou.androiduiview.R;

/**
 * Created by ihgoo on 2015/8/25.
 */
public class UIDialog {

    private Context   mContext;
    private ViewGroup decorView;
    private ViewGroup rootView;

    ViewGroup contentContainer;

    private UIDialogClickListener mDialogClickListener;

    private TextView tvTitile;
    private TextView tvContent;
    private TextView tvConfirm;
    private TextView tvCancel;

    private UIDialog() {
    }

    public UIDialog(Context context) {
        this.mContext = context;
    }

    public void showQuestionDialog(String title, String content, UIDialogClickListener uiDialogClickListener) {
        prepare(UIDialogStyle.Question_Style, title, content, null, null, uiDialogClickListener);
        build();
    }

    public void showQuestionDialog(String title, String content, String confirmText,
                                   UIDialogClickListener uiDialogClickListener) {
        prepare(UIDialogStyle.Question_Style, title, content, confirmText, null, uiDialogClickListener);
        build();
    }

    public void showQuestionDialog(String title, String content, String confirmText, String cancelText,
                                   UIDialogClickListener uiDialogClickListener) {
        prepare(UIDialogStyle.Question_Style, title, content, confirmText, cancelText, uiDialogClickListener);
        build();
    }

    public void showTipDialog(String content,
                              UIDialogClickListener uiDialogClickListener) {
        prepare(UIDialogStyle.Tip_Style, null, content, null, null, uiDialogClickListener);
        build();
    }

    public void showTipDialog(String title, String content, String confirmText,
                              UIDialogClickListener uiDialogClickListener) {
        prepare(UIDialogStyle.Tip_Style, title, content, confirmText, null, uiDialogClickListener);
        build();
    }

    void prepare(int style, String title, String content, String confirmText, String cancelText,
                 UIDialogClickListener uiDialogClickListener) {
        if (style == 0) {
            throw new UnsupportedOperationException("not support this style.");
        }

        initView(style);

        if (null != title) {
            setTitile(title);
        }

        if (null != content) {
            setContent(content);
        }
        if (null != cancelText) {
            setCancelText(cancelText);
        }

        if (null != confirmText) {
            setConfirmText(confirmText);
        }

        if (null != uiDialogClickListener) {
            setClickListener(uiDialogClickListener);
        }

    }

    private void initView(int style) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        decorView = (ViewGroup) ((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content);
        rootView = (ViewGroup) layoutInflater.inflate(R.layout.layout_dialog, decorView, false);
        contentContainer = (ViewGroup) rootView.findViewById(R.id.ll_content_container);

        tvTitile = (TextView) rootView.findViewById(R.id.tv_title);
        tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        tvConfirm = (TextView) rootView.findViewById(R.id.tv_confirm);
        tvCancel = (TextView) rootView.findViewById(R.id.tv_cancel);

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogClickListener != null) {
                    mDialogClickListener.confirm();
                }
                dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogClickListener != null) {
                    mDialogClickListener.cancel();
                }
                dismiss();
            }
        });

        if (style == UIDialogStyle.Question_Style) {
            tvCancel.setVisibility(View.VISIBLE);
        } else if (style == UIDialogStyle.Tip_Style) {
            tvCancel.setVisibility(View.GONE);
        }

    }

    private void attached(View view) {
        decorView.addView(view);
        contentContainer.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_in_center));
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

    public boolean isShowing() {
        View view = decorView.findViewById(R.id.ll_content_container);
        return view != null;
    }

    public UIDialog setCancelText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tvCancel.setText(text);
        }
        return this;
    }

    public UIDialog setConfirmText(String text) {
        if (!TextUtils.isEmpty(text)) {
            tvConfirm.setText(text);
        }
        return this;
    }

    public UIDialog setTitile(String title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitile.setText(title);
        }
        return this;
    }

    public UIDialog setTitile(int resId) {
        setTitile(mContext.getResources().getString(resId));
        return this;
    }

    public UIDialog setContent(String content) {
        if (!TextUtils.isEmpty(content)) {
            tvContent.setText(content);
        }
        return this;
    }

    public UIDialog setContent(int resId) {
        setContent(mContext.getResources().getString(resId));
        return this;
    }

    public UIDialog setClickListener(UIDialogClickListener dialogClickListener) {
        mDialogClickListener = dialogClickListener;
        return this;
    }

    /**
     * like show()
     */
    public void build() {
        if (isShowing()) {
            return;
        }
        attached(rootView);
    }

}
