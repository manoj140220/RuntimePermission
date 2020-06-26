package com.manoj.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.manoj.runtime.RuntimePermission;
import com.manoj.runtime.notifier.PermissionNotify;

public class MainActivity extends AppCompatActivity implements PermissionNotify {

    public static final String[] STORAGE_PERMISSION = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BODY_SENSORS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RuntimePermission(this, STORAGE_PERMISSION, this);
    }

    @Override
    public void notifyPermissionGrant() {
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyPermissionDeny() {
        Toast.makeText(this, "Permission Deny", Toast.LENGTH_SHORT).show();
    }

}
