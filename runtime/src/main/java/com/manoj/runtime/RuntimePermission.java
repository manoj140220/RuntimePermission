package com.manoj.runtime;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;

import com.manoj.runtime.notifier.NotifyPermission;
import com.manoj.runtime.notifier.PermissionNotify;

/**
 * Created By : Manoj DB on 25/6/20
 */
public class RuntimePermission implements NotifyPermission {

    private PermissionNotify permissionNotify;
    private String[] permissionData;
    private static final int RUNTIME_PERMISSION_REQUEST_CODE = 49;
    static final String PERMISSION_ARRAY = "PERMISSION_ARRAY";
    private Activity activity;
    static NotifyPermission notifyPermission;

    public RuntimePermission() {
        /**
         * Default Constructor, to access other public methods.
         * */
    }

    public RuntimePermission(PermissionNotify permissionNotify,
                             String[] permissionObject,
                             Activity activity) {
        this.permissionNotify = permissionNotify;
        this.permissionData = permissionObject;
        this.activity = activity;
        notifyPermission = this;
        if (!hasPermissionsGranted(permissionData))
            requestPermission(permissionData);
        else
            this.permissionNotify.notifyPermissionGrant();

    }

    private void requestPermission(String[] permissionData) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Intent intent = new Intent(activity, RuntimePermissionActivity.class);
            intent.putExtra(PERMISSION_ARRAY, permissionData);
            activity.startActivity(intent);
        } else
            permissionNotify.notifyPermissionGrant();
    }

    public boolean hasPermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(activity, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    /**
     * The below are the dangerous permissions, handled when user click on never ask again
     */
    private boolean getRationalPermission() {
        return !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALENDAR) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALENDAR) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CONTACTS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.GET_ACCOUNTS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_NUMBERS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CALL_PHONE) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ANSWER_PHONE_CALLS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_CALL_LOG) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ADD_VOICEMAIL) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.USE_SIP) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.PROCESS_OUTGOING_CALLS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.BODY_SENSORS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.SEND_SMS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_WAP_PUSH) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_MMS) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                !ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void notifyPermission(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == RUNTIME_PERMISSION_REQUEST_CODE) {
            boolean isPermissionGranted = true;
            if (grantResults.length == permissions.length) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        isPermissionGranted = false;
                        break;
                    }
                }

                if (!isPermissionGranted) {
                    if (getRationalPermission()) {
                        permissionNotify.notifyPermissionDeny();
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                            requestPermission(permissionData);
                        else
                            permissionNotify.notifyPermissionGrant();
                    }
                } else
                    permissionNotify.notifyPermissionGrant();
            }
        }
    }
}