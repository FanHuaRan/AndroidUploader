package fhr.com.androiduploader.core.chunker;

import java.io.InputStream;

import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件切片接口
 * Created by fhr on 2017/6/4.
 */
public interface Chunker {
    int computeChunks(UploadFile file);

    InputStream getChunkData(UploadFile file, int chunk);
}
