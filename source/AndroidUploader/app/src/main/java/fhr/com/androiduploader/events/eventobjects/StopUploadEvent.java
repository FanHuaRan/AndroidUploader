package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;

/**
 * 文件上传流程暂停事件
 * Created by fhr on 2017/6/2.
 */

public class StopUploadEvent extends UploaderEvent {

    public StopUploadEvent(AndroidUploader uploader) {
        super(uploader);
    }
}
