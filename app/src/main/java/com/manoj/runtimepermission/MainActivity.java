package com.manoj.runtimepermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.manoj.runtime.RuntimePermission;
import com.manoj.runtime.notifier.PermissionNotify;

public class MainActivity extends AppCompatActivity implements PermissionNotify {

    protected static final String[] storagePermission = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!new RuntimePermission().hasPermissionsGranted(storagePermission, this))
            new RuntimePermission(this, storagePermission, this);
        else
            Toast.makeText(this, "All permission set.", Toast.LENGTH_SHORT).show();
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
