package com.sakharuk.vkrandommusic.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakharuk.vkrandommusic.R;
import com.sakharuk.vkrandommusic.databinding.FragmentLoginBinding;
import com.vk.sdk.VKSdk;

public class LoginFragment extends Fragment {

    private static final String[] scope = new String[]{"audio"};

    public class Handlers {
        public void onLogin(View view) {
            VKSdk.login(getActivity(), scope);
        }
    }

    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.setHandlers(new Handlers());
        return binding.getRoot();
    }
}
