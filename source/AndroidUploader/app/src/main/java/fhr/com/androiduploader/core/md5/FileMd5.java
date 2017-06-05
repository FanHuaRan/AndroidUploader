package fhr.com.androiduploader.core.md5;

import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件MD5计算接口
 * Created by fhr on 2017/6/4.
 */

public interface FileMd5 {
     String computeTotalMd5(UploadFile uploadFile) throws Exception;
}
