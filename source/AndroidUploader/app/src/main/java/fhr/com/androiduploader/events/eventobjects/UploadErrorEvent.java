package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件上传出错事件
 * Created by fhr on 2017/6/2.
 */

public class UploadErrorEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;
    /**
     * 错误标识
     */
    private final String errorCode;

    public UploadErrorEvent(AndroidUploader uploader,UploadFile file,String errorCode) {
        super(uploader);
        this.file = file;
        this.errorCode=errorCode;
    }

    public UploadFile getFile() {
        return file;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
