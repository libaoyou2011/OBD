package com.baolong.obd.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.baolong.obd.R;


public class DialogManager {

    public static Dialog showConfirmDialog(Context context, String text, View.OnClickListener cancelOnClickListener, View.OnClickListener confirmOnClickListener) {
        Dialog dialog = new Dialog(context, R.style.product_dialog_style);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common_confirm, null);
        dialog.setContentView(view);  // 必须放到 Window配置 前面

        // View
        ((TextView) view.findViewById(R.id.tv_content)).setText(text);
        TextView cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        TextView confirmTextView = (TextView) view.findViewById(R.id.tv_confirm);
        cancelTextView.setOnClickListener((v) -> {
            cancelOnClickListener.onClick(view);
            dialog.dismiss();
        });
        confirmTextView.setOnClickListener((v) -> {
            confirmOnClickListener.onClick(view);
            dialog.dismiss();
        });

        // 布局设置
        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);

            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = (int) context.getResources().getDimension(R.dimen.x548);
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(layoutParams);
        }

        return dialog;
    }

    public static Dialog showConfirmDialog(Context context, String text, View.OnClickListener onClickListener1, String text2, View.OnClickListener onClickListener2, String text3) {
        Dialog dialog = new Dialog(context, R.style.product_dialog_style);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common_confirm, null);
        ((TextView) view.findViewById(R.id.tv_content)).setText(text);
        TextView cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        cancelTextView.setText(text2);
        TextView confirmTextView = (TextView) view.findViewById(R.id.tv_confirm);
        confirmTextView.setText(text3);
        cancelTextView.setOnClickListener((v) -> {
            onClickListener1.onClick(view);
            dialog.dismiss();
        });
        confirmTextView.setOnClickListener((v) -> {
            onClickListener2.onClick(view);
            dialog.dismiss();
        });
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        window.setFlags(4, 4);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) context.getResources().getDimension(R.dimen.x548);
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(attributes);
        return dialog;
    }

    public static Dialog showConfirmInputDialog(Context context, String text, final OnItemClickListener onItemClickListener1, String text2, final OnItemClickListener onItemClickListener2, String text3) {
        Dialog dialog = new Dialog(context, R.style.product_dialog_style);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_common_confirm_input, null);
        TextView titleTextView = (TextView) view.findViewById(R.id.tv_content);
        titleTextView.setText(text);
        TextView cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        cancelTextView.setText(text2);
        TextView confirmTextView = (TextView) view.findViewById(R.id.tv_confirm);
        confirmTextView.setText(text3);
        EditText editText = (EditText) view.findViewById(R.id.et_yj);
        cancelTextView.setOnClickListener((v) -> {
            onItemClickListener1.onItemClick(v, editText.getText().toString().trim());
            dialog.dismiss();
        });
        confirmTextView.setOnClickListener((v) -> {
            onItemClickListener2.onItemClick(v, editText.getText().toString().trim());
            dialog.dismiss();
        });
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        window.setFlags(4, 4);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) context.getResources().getDimension(R.dimen.x600);
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setGravity(Gravity.CENTER);
        window.setAttributes(attributes);
        return dialog;
    }

    public static Dialog showServerInputDialog(Context context, String titleText, OnItemClickListener onItemClickListener1, String cancelText, OnItemClickListener onItemClickListener2, String confirmText
            , String serverUrlText) {
        Dialog dialog = new Dialog(context, R.style.product_dialog_style);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_server_input, null);
        TextView titleTextView = (TextView) view.findViewById(R.id.tv_content);
        titleTextView.setText(titleText);
        TextView cancelTextView = (TextView) view.findViewById(R.id.tv_cancel);
        cancelTextView.setText(cancelText);
        TextView confirmTextView = (TextView) view.findViewById(R.id.tv_confirm);
        confirmTextView.setText(confirmText);
        EditText editText = (EditText) view.findViewById(R.id.et_yj);
        editText.setText(serverUrlText);
        cancelTextView.setOnClickListener((v -> {
            onItemClickListener1.onItemClick(view, editText.getText().toString().trim());
            dialog.dismiss();
        }));
        confirmTextView.setOnClickListener((v) -> {
            onItemClickListener2.onItemClick(view, editText.getText().toString().trim());
            dialog.dismiss();
        });
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
            window.setFlags(WindowManager.LayoutParams.FLAGS_CHANGED, WindowManager.LayoutParams.FLAGS_CHANGED);

            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) context.getResources().getDimension(R.dimen.x600);
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(attributes);
        }
        return dialog;
    }

    public static abstract interface OnItemClickListener {
        public abstract void onItemClick(View view, String s);
    }
}
