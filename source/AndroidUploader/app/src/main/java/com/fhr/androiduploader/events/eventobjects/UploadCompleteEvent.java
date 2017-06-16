package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 单个文件上传完成事件，不管成功还是失败
 * Created by fhr on 2017/6/2.
 */

public class UploadCompleteEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;

    public UploadCompleteEvent(AndroidUploader uploader,UploadFile file) {
        super(uploader);
        this.file = file;
    }

    public UploadFile getFile() {
        return file;
    }
}
