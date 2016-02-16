package com.sakharuk.vkrandommusic.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakharuk.vkrandommusic.R;
import com.sakharuk.vkrandommusic.databinding.FragmentPlayerBinding;
import com.sakharuk.vkrandommusic.service.VkRequestService;
import com.sakharuk.vkrandommusic.service.VkRequestServiceConnection;

public class PlayerFragment extends Fragment {

    public class Handlers {
        public void onPlay(View view) {

        }
    }

    public static final int RESPONSE_HANDLER

    private FragmentPlayerBinding binding;
    private VkRequestServiceConnection serviceConnection = new VkRequestServiceConnection();
    private VkRequestService.VkRequestServiceBinder binder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);
        binding.setHandlers(new Handlers());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().bindService(
                new Intent(getActivity(), VkRequestService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE
        );
        while (!serviceConnection.isConnected()) {}
        binder = serviceConnection.getBinder();
        binder.setResponseHandler();
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unbindService(serviceConnection);
    }
}
