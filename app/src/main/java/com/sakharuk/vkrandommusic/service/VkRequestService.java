package com.sakharuk.vkrandommusic.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class VkRequestService extends Service {

    public static final String RESPONSE_HANDLER_ID = "response_handler_id";

    public class VkRequestServiceBinder extends Binder {

        public VkRequestService getService() {
            return VkRequestService.this;
        }

        public void setResponseHandler(int handlerId, Handler handler) {
            responseHandlers.put(handlerId, handler);
        }
    }

    private VkRequestServiceBinder binder;
    private SparseArray<Handler> responseHandlers;
    private ExecutorService executor = Executors.newCachedThreadPool();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        binder = new VkRequestServiceBinder();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        int id = intent.getIntExtra(RESPONSE_HANDLER_ID, -1);
        if (id >= 0) {
            responseHandlers.delete(id);
        }
        return super.onUnbind(intent);
    }

    public void sendVkRequest(final VKRequest request, final int handlerId, final int what) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                request.executeSyncWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        Handler handler = responseHandlers.get(handlerId);
                        if (handler != null) {
                            handler.sendMessage(handler.obtainMessage(what, response));
                        }
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }

                    @Override
                    public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                        super.onProgress(progressType, bytesLoaded, bytesTotal);
                    }
                });
            }
        });
    }
}
