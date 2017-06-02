package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;

/**
 * 当某个文件上传到服务端响应后，会派送此事件来询问服务端响应是否有效 这个不一定使用
 * Created by fhr on 2017/6/2.
 */

public class UploadAcceptEvent extends UploaderEvent {

    public  UploadAcceptEvent(AndroidUploader uploader){
        super((uploader));
    }
}

