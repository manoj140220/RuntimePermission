package com.manoj.runtime;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.manoj.runtime.RuntimePermission.PERMISSION_ARRAY;
import static com.manoj.runtime.RuntimePermission.notifyPermission;

/**
 * Created By : Manoj DB on 25/6/20
 */
public class RuntimePermissionActivity extends Activity {
    private static final int RUNTIME_PERMISSION_REQUEST_CODE = 49;
    private String[] permissionData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runtime_activity);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null)
            permissionData = bundle.getStringArray(PERMISSION_ARRAY);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            assert permissionData != null;
            requestPermissions(permissionData, RUNTIME_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        notifyPermission.notifyPermission(requestCode, permissions, grantResults);
        finish();
    }
}