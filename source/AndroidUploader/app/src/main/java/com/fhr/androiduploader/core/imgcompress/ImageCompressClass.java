package com.fhr.androiduploader.core.imgcompress;

import android.graphics.drawable.Drawable;

import com.fhr.androiduploader.models.UploadFile;

/**
 * 图片压缩实现
 * Created by fhr on 2017/6/4.
 */

public class ImageCompressClass implements ImageCompress {
    /**
     * 压缩参数
     */
    private final CompressParam compressParam;

    public ImageCompressClass(CompressParam compressParam) {
        this.compressParam = compressParam;
    }

    @Override
    public Drawable compressImg(UploadFile uploadFile) {
        return null;
    }
}
