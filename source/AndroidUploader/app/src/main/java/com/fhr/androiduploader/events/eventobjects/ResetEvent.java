package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * uploader重置事件
 * Created by fhr on 2017/6/2.
 */

public class ResetEvent extends UploaderEvent {

    public ResetEvent(AndroidUploader uploader) {
        super(uploader);
    }

}
