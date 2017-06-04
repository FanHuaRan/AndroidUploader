package fhr.com.androiduploader.core.imgcompress;

import android.graphics.drawable.Drawable;

import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 图片压缩接口
 * Created by fhr on 2017/6/4.
 */

public interface ImageCompress {
    Drawable compressImg(UploadFile uploadFile);
}
