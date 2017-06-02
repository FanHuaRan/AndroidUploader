package fhr.com.androiduploader.events.eventobjects;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.core.ErrorType;
import fhr.com.androiduploader.events.UploaderEvent;

/**
 * Uploader控件发生总的校验错误 事件
 * Created by fhr on 2017/6/2.
 */

public class ErrorEvent extends UploaderEvent {
    /**
     * 错误枚举类型
     */
    private ErrorType errorType;

    public  ErrorEvent(AndroidUploader uploader,ErrorType errorType){
        super(uploader);
        this.errorType=errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
