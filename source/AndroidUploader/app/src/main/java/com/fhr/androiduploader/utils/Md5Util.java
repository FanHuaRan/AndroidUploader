package com.fhr.androiduploader.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * MD5辅助类
 * Created by fhr on 2017/6/5.
 */

public class Md5Util {
    /**
     * 缓冲字节数
     */
    private static  final  int BUFFER_SIZE=1024;

    /**
     * 计算文件MD5
     * @param file
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public  static  String computeFileMd5(File file) throws IOException, NoSuchAlgorithmException {
        FileInputStream fis=null;
        try {
             fis = new FileInputStream(file);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[BUFFER_SIZE];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            BigInteger bigInt = new BigInteger(1, md.digest());
            return  bigInt.toString();
        }finally {
            SimpleIOUtil.closeIO(fis);
        }
    }

    /**
     * 计算字节数组的MD5
     * @param bytes
     * @return
     * @throws NoSuchAlgorithmException
     */
    public  static String computeBytesMd5(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes, 0, bytes.length);
        BigInteger bigInt = new BigInteger(1, md.digest());
        return  bigInt.toString();
    }

    /**
     * 计算字节数组集合的MD5
     * @param bytesArray
     * @return
     * @throws NoSuchAlgorithmException
     */
    public  static  String computBytesArrayMd5(List<byte[]> bytesArray) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        for (byte[] bytes :bytesArray){
            md.update(bytes, 0, bytes.length);
        }
        BigInteger bigInt = new BigInteger(1, md.digest());
        return  bigInt.toString();
    }

}
