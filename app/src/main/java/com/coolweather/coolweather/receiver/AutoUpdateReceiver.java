package com.coolweather.coolweather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.coolweather.coolweather.service.AutoUpdateService;
import com.coolweather.coolweather.util.LogUtil;

/**
 * Created on 2016/4/4.
 */
public class AutoUpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.e("AutoUpdateReceiver", "AutoUpdateReceiver");
        Intent i = new Intent(context, AutoUpdateService.class);
        i.putExtra("is_first", false);
        context.startService(i);
    }
}
