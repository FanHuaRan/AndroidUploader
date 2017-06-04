package fhr.com.androiduploader.core.chunker;

import java.io.InputStream;

import fhr.com.androiduploader.core.AndroidUploader;
import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件切片实现
 * Created by fhr on 2017/6/4.
 */

class ChunkerClass implements Chunker {
    /**
     * uploader控件
     */
    private final AndroidUploader uploader;
    /**
     * 分片大小
     */
    private final int chunkedSize;

    /**
     * constructor
     * @param uploader
     * @param chunkedSize
     */
    public ChunkerClass(AndroidUploader uploader, int chunkedSize) {
        this.uploader = uploader;
        this.chunkedSize = chunkedSize;
    }

    /**
     * 计算文件块数
     * @param file
     * @return
     */
    @Override
    public int computeChunks(UploadFile file){
        return 0;
    }

    /**
     * 获取对应块的文件数据
     * @param file
     * @param chunk
     * @return
     */
    @Override
    public InputStream getChunkData(UploadFile file, int chunk){
        return  null;
    }

}
