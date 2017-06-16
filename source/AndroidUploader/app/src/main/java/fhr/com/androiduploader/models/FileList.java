package fhr.com.androiduploader.models;

import java.util.Vector;

import fhr.com.androiduploader.core.AndroidUploader;

/**
 * 文件集合 线程安全
 * Created by fhr on 2017/6/1.
 */

public class FileList extends Vector<UploadFile> {
    /**
     * 文件总大小
     */
    private int totalSize;
    /**
     * 文件上传组件
     */
    private final AndroidUploader uploader;

    /**
     * constructor
     * @param uploader
     */
    public   FileList(AndroidUploader uploader){
        this.uploader=uploader;
        this.totalSize=0;
    }

    public int getTotalSize() {
        return totalSize;
    }
}
