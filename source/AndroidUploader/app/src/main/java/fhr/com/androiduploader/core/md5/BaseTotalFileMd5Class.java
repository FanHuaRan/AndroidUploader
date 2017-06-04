package fhr.com.androiduploader.core.md5;

import fhr.com.androiduploader.models.UploadFile;

/**
 * 分端MD5计算实现
 * Created by fhr on 2017/6/4.
 */

public class BaseTotalFileMd5Class implements FileMd5 {
    /**
     * 块数
     */
    private final int chunkCount;

    /**
     * 每一块计算前面的多少字节
     */
    private final int computeSingleBytes;

    public BaseTotalFileMd5Class(int chunkCount, int computeSingleBytes) {
        this.chunkCount = chunkCount;
        this.computeSingleBytes = computeSingleBytes;
    }

    @Override
    public String computeTotalMd5(UploadFile uploadFile) {
        return null;
    }
}
