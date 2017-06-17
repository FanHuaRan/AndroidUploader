package com.fhr.androiduploader.core.chunker;


import com.fhr.androiduploader.core.AndroidUploader;
import com.fhr.androiduploader.models.UploadFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * 文件切片实现
 * Created by fhr on 2017/6/4.
 */

public class ChunkerClass implements Chunker {
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
     * @param uploadFile
     * @return
     */
    @Override
    public int computeChunks(UploadFile uploadFile){
        int chunks=(int)uploadFile.getSize()/chunkedSize;
        if(uploadFile.getSize()%chunkedSize!=0){
            chunks++;
        }
        return chunks;
    }

    /**
     * 获取对应块的文件数据
     * @param uploadFile
     * @param chunk
     * @return
     */
    @Override
    public InputStream getChunkData(UploadFile uploadFile, int chunk) throws IOException {
//        try(RandomAccessFile randomAccessFile=new RandomAccessFile(uploadFile.getFilePath(),"r")){
//        }
        return  null;
    }

    @Override
    public byte[] getChunDataBytes(UploadFile uploadFile, int chunk) throws IOException {
        try(RandomAccessFile randomAccessFile=new RandomAccessFile(uploadFile.getFilePath(),"r")){
            int off=(chunk-1)*chunkedSize;
            byte[]bytes=new byte[chunkedSize];
            randomAccessFile.skipBytes(off);
            //这儿有问题
            int readLength=randomAccessFile.read(bytes);
            return  bytes;
        }
    }

}
