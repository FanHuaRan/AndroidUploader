package com.fhr.androiduploader.core.md5;

import com.fhr.androiduploader.models.UploadFile;
import com.fhr.androiduploader.utils.Md5Util;
import com.fhr.androiduploader.utils.SimpleFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 分端MD5计算实现
 * Created by fhr on 2017/6/4.
 */

public class BaseSubsectionFileMd5Class implements FileMd5 {
    /**
     * 块数
     */
    private final int subs;

    /**
     * 每一块计算前面的多少字节
     */
    private final int computeSingleBytes;

    public BaseSubsectionFileMd5Class(int subs, int computeSingleBytes) {
        this.subs = subs;
        this.computeSingleBytes = computeSingleBytes;
    }

    @Override
    public String computeTotalMd5(UploadFile uploadFile) throws Exception {
        File file=new File(uploadFile.getFilePath());
        int subsSize=(int)uploadFile.getSize()/subs;
        List<byte[]> bytesArray=new ArrayList<>();
        for(int i=0;i<subs;i++){
            int start=subsSize*i;
            bytesArray.add(SimpleFileUtil.getFileBytes(file,start,computeSingleBytes));
        }
        return Md5Util.computBytesArrayMd5(bytesArray);
    }
}
