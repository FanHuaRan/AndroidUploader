package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 单个文件上传过程进度事件
 * Created by fhr on 2017/6/2.
 */

public class UploadProgressEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;
    /**
     * 上传进度
     */
    private final int percentage;
    public UploadProgressEvent(AndroidUploader uploader,UploadFile file,int percentage) {
        super(uploader);
        this.file = file;
        this.percentage=percentage;
    }

    public UploadFile getFile() {
        return file;
    }

    public int getPercentage() {
        return percentage;
    }
}
