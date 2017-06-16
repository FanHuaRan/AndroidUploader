package fhr.com.androiduploader.utils;

import java.io.Closeable;

/**
 * IO辅助类
 * Created by fhr on 2017/6/5.
 */

public class SimpleIOUtil {
    /**
     * 安全关闭IO
     * @param closeables
     */
    public static void closeIO(Closeable ...closeables){
        for (Closeable closeable :closeables){
            if(closeable!=null){
                try{
                    closeable.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

}
