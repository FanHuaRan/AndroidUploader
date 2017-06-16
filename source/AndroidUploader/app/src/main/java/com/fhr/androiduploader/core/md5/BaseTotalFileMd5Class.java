package fhr.com.androiduploader.core.md5;

import java.io.File;

import fhr.com.androiduploader.models.UploadFile;
import fhr.com.androiduploader.utils.Md5Util;

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
