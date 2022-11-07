package org.mediasoup.droid.demo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        initVersion();
        checkPermission();
    }

    private void initVersion() {
        TextView tvVer = (TextView) findViewById(R.id.text_ver);
        tvVer.setText(BuildConfig.VERSION_NAME + "-" + BuildConfig.VERSION_CODE);
    }

    private void enterMain() {
        findViewById(R.id.mediasoup).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, RoomActivity.class));
                finish();
            }
        }, 1500);
    }

    private PermissionHandler permissionHandler =
            new PermissionHandler() {
                @Override
                public void onGranted() {
                    enterMain();
                }
            };

    private void checkPermission() {
        String[] permissions = {
                Manifest.permission.INTERNET,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        String rationale = "Please provide permissions";
        Permissions.Options options =
                new Permissions.Options().setRationaleDialogTitle("Info").setSettingsDialogTitle("Warning");
        Permissions.check(this, permissions, rationale, options, permissionHandler);
    }
}
