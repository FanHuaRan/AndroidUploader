package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 单个文件上传成功事件
 * Created by fhr on 2017/6/2.
 */

public class UploadSuccessEvent extends UploaderEvent {
    /**
     * 文件
     */
    private final UploadFile file;
    /**
     * 服务端返回数据 最好是json字符串
     */
    private final String response;

    public UploadSuccessEvent(AndroidUploader uploader, UploadFile file,String response) {
        super(uploader);
        this.file = file;
        this.response=response;
    }

    public UploadFile getFile() {
        return file;
    }

    public String getResponse() {
        return response;
    }
}
