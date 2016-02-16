package com.sakharuk.vkrandommusic;

import android.app.Application;
import android.content.Intent;

import com.sakharuk.vkrandommusic.service.VkRequestService;
import com.vk.sdk.VKSdk;

public class VkRandomAudioApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);

        startService(new Intent(this, VkRequestService.class));
    }
}
