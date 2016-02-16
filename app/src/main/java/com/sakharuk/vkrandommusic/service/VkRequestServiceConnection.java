package com.sakharuk.vkrandommusic.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * Created by saharuk on 16.02.16.
 */
public class VkRequestServiceConnection implements ServiceConnection {

    private boolean isConnected;
    private VkRequestService.VkRequestServiceBinder binder;

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        binder = (VkRequestService.VkRequestServiceBinder) iBinder;
        isConnected = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isConnected = false;
        binder = null;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public VkRequestService.VkRequestServiceBinder getBinder() {
        return binder;
    }
}
