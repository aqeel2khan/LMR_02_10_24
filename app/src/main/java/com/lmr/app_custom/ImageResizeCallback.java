package com.lmr.app_custom;

import android.graphics.Bitmap;

import java.io.File;

public interface ImageResizeCallback {
    void onSuccess(String base64String, Bitmap bitmap, File file);
    void onFailure(String msg);
}
