package com.fhr.androiduploader.core.imgcompress;

import android.graphics.drawable.Drawable;

import com.fhr.androiduploader.models.UploadFile;

/**
 * 图片压缩接口
 * Created by fhr on 2017/6/4.
 */

public interface ImageCompress {
    Drawable compressImg(UploadFile uploadFile);
}
