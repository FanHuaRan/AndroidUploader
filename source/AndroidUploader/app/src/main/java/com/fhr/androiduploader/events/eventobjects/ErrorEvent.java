package com.fhr.androiduploader.events.eventobjects;

import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.types.ErrorType;
import com.fhr.androiduploader.events.UploaderEvent;

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
