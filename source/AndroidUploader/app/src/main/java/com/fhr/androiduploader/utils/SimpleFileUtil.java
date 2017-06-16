package fhr.com.androiduploader.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 简单文件辅助类
 * Created by fhr on 2017/6/5.
 */

public class SimpleFileUtil {
    /**
     * 获取文件制定位置起始的N个字节
     * @param file
     * @param start
     * @param length
     * @return
     * @throws IOException
     */
    public  static byte[] getFileBytes(File file,int start,int length) throws IOException {
        byte[]datas=new byte[length];
        RandomAccessFile randomAccessFile=new RandomAccessFile(file,"r");
        randomAccessFile.read(datas,start,length);
        return datas;
    }
}
