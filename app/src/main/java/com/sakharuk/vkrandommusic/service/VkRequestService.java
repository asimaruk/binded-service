package com.sakharuk.vkrandommusic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.vk.sdk.api.VKRequest;

public class VkRequestService extends Service {

    public class LocalBinder extends Binder {

        public void sendVkRequest(VKRequest request) {
            new Thread()
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }
}
