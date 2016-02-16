package com.sakharuk.vkrandommusic.fragment;

import android.app.Fragment;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakharuk.vkrandommusic.R;
import com.sakharuk.vkrandommusic.databinding.FragmentPlayerBinding;

public class PlayerFragment extends Fragment {

    public class Handlers {
        public void onPlay(View view) {

        }
    }

    private FragmentPlayerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false);
        binding.setHandlers(new Handlers());
        return binding.getRoot();
    }
}
