package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 某个文件开始上传事件
 * Created by fhr on 2017/6/2.
 */

public class UploadStartEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;

    public UploadStartEvent(AndroidUploader uploader,UploadFile file) {
        super(uploader);
        this.file = file;
    }

    public UploadFile getFile() {
        return file;
    }
}
