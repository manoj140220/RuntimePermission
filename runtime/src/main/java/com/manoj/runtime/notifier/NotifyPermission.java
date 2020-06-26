package com.manoj.runtime.notifier;

/**
 * Created By : Manoj DB on 26/6/20
 */
public interface NotifyPermission {
    void notifyPermission(int requestCode, String[] permissions, int[] grantResults);
}
