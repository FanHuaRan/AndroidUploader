package fhr.com.androiduploader.core.chunker;

import java.io.IOException;
import java.io.InputStream;

import fhr.com.androiduploader.models.UploadFile;

/**
 * 文件切片接口
 * Created by fhr on 2017/6/4.
 */
public interface Chunker {
    int computeChunks(UploadFile uploadFile);

    InputStream getChunkData(UploadFile uploadFile, int chunk) throws IOException;

    byte[] getChunDataBytes(UploadFile uploadFile,int chunk) throws IOException;
}
