package com.fhr.androiduploader.core.md5;

import com.fhr.androiduploader.models.UploadFile;
import com.fhr.androiduploader.utils.Md5Util;

import java.io.File;

/**
 * 总文件的MD5计算实现
 * Created by fhr on 2017/6/4.
 */

public class BaseTotalFileMd5Class implements  FileMd5{
    @Override
    public String computeTotalMd5(UploadFile uploadFile) throws Exception {
        File file=new File(uploadFile.getFilePath());
        try{
           return Md5Util.computeFileMd5(file);
        }catch (Exception e) {
            throw  new Exception("MD5计算出错",e);
        }
    }
}
