package com.ntkhai.broadcastreceiverdemo.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class CustomReceiver extends BroadcastReceiver {

    public static final String CUSTOM_ACTION = "com.ntkhai.broadcastreceiverdemo.CUSTOM_ACTION";
    public static final String EXTRA_MESSAGE = "com.ntkhai.broadcastreceiverdemo.EXTRA_MESSAGE";

    public interface OnCustomBroadcastListener {
        void onCustomBroadcastReceived(String message);
    }

    private OnCustomBroadcastListener listener;

    public CustomReceiver() {}

    public CustomReceiver(OnCustomBroadcastListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (CUSTOM_ACTION.equals(intent.getAction())) {
            String message = intent.getStringExtra(EXTRA_MESSAGE);
            if (listener != null) {
                listener.onCustomBroadcastReceived(message);
            }
        }
    }
}
