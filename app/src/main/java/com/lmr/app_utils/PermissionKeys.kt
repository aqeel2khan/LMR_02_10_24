package com.lmr.app_utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat



object PermissionKeys {
    private val INTENT_REQUEST_GET_IMAGES = 13
    val gallery = 12
    const val RC_FILE_PICKER_PERM = 321

    val MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123
    //request code for permission
    const val REQUEST_CODE_ACCESS_FINE_LOCATION = 1
    const val REQUEST_CODE_ACCESS_COARSE_LOCATION = 2
    const val REQUEST_CODE_ACCESS_NETWORK_STATE = 3
    const val REQUEST_CODE_ACCESS_WIFI_STATE = 4
    const val REQUEST_CODE_CALL_PHONE = 5
    const val REQUEST_CODE_CAMERA = 7
    const val REQUEST_CODE_READ_CONTACTS = 8
    const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 9
    const val REQUEST_CODE_READ_PHONE_STATE = 10
    const val REQUEST_CODE_WAKE_LOCK = 11
    const val REQUEST_CODE_WRITE_EXTERNAL_STORAGE = 12
    const val REQUEST_CODE_PERMISSION_ALL = 13
    const val internalFileRequest = 10

    //permission name
    const val PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    const val PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    const val PERMISSION_ACCESS_NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE
    const val PERMISSION_ACCESS_WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE
    const val PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE
    const val PERMISSION_CAMERA = Manifest.permission.CAMERA
    const val PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS
    const val PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    const val PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE
    const val PERMISSION_WAKE_LOCK = Manifest.permission.WAKE_LOCK
    const val PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    const val MANAGE_EXTERNAL_STORAGE = Manifest.permission.MANAGE_EXTERNAL_STORAGE
     var permissions = arrayOf(
        PERMISSION_CAMERA,
        PERMISSION_READ_EXTERNAL_STORAGE,
        PERMISSION_WRITE_EXTERNAL_STORAGE
    )
     fun checkPermissionREAD_EXTERNAL_STORAGE(context: Context?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context!!,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) !== PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (context as Activity?)!!,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    //  showDialog("External storage", context, Manifest.permission.READ_EXTERNAL_STORAGE);
                } else {
                    ActivityCompat
                        .requestPermissions(
                            context as Activity,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                        )
                }
                false
            } else {
                true
            }
        } else {
            true
        }
    }
     @RequiresApi(Build.VERSION_CODES.R)
     fun checkPermission(context: Context): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                ActivityCompat.requestPermissions(
                    context as Activity, arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                    ), MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE
                )
                return false
            }
        } else {
            return true
        }
    }
     fun requestPermission(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                intent.addCategory("android.intent.category.DEFAULT")
                intent.data = Uri.parse(String.format("package:%s", context.packageName))
                (context as Activity).startActivityForResult(intent, gallery)
            } catch (e: java.lang.Exception) {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION
                (context as Activity).startActivityForResult(intent, gallery)
            }
        }
    }
    fun checkPermission1(context: Context): Boolean {
        return if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is not granted
            false
        } else true
    }


}