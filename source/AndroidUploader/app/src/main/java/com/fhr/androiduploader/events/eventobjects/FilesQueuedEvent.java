package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 一批文件加入队列之后事件
 * Created by fhr on 2017/6/2.
 */

public class FilesQueuedEvent  extends UploaderEvent {
    /**
     * 文件数组
     */
    private final UploadFile[] files;

    public FilesQueuedEvent(AndroidUploader uploader,UploadFile[] files) {
        super(uploader);
        this.files = files;
    }

    public UploadFile[] getFiles() {
        return files;
    }
}
