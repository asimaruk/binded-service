package com.sakharuk.vkrandommusic;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.sakharuk.vkrandommusic.databinding.ActivityMainBinding;
import com.sakharuk.vkrandommusic.fragment.LoginFragment;
import com.sakharuk.vkrandommusic.fragment.PlayerFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (!VKSdk.isLoggedIn()) {
            switchFragment(LoginFragment.class.getCanonicalName());
        } else {
            if (getFragmentManager().findFragmentById(binding.fragmentContainer.getId()) == null) {
                switchFragment(PlayerFragment.class.getCanonicalName());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                res.save();
                switchFragment(PlayerFragment.class.getCanonicalName());
            }

            @Override
            public void onError(VKError error) {
                Snackbar.make(binding.getRoot(), R.string.login_error, Snackbar.LENGTH_SHORT).show();
            }
        }))
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void switchFragment(String fragmentName) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        int containerId = binding.fragmentContainer.getId();

        Fragment currentFragment = fm.findFragmentById(containerId);
        if (currentFragment != null) {
            if (TextUtils.equals(currentFragment.getClass().getCanonicalName(), fragmentName)) {
                return;
            }
            ft.detach(currentFragment);
        }

        Fragment f = fm.findFragmentByTag(fragmentName);
        if (f == null) {
            f = Fragment.instantiate(this, fragmentName);
            ft.add(containerId, f, fragmentName);
        } else if (f.isDetached()) {
            ft.attach(f);
        }
        ft.commit();
    }
}
