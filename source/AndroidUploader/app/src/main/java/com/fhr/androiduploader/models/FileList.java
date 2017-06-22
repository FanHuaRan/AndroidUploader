package com.fhr.androiduploader.models;

import com.fhr.androiduploader.core.AndroidUploader;

import java.util.Collection;
import java.util.Vector;

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

    /**
     * uploadFile
     * @param fileId
     * @return
     */
    public  UploadFile  removeByFileId(String fileId) {
        for (UploadFile uploadFile : this) {
            if (uploadFile.getFileId().equals("fileId")) {
                this.remove(uploadFile);
                return uploadFile;
            }
        }
        return null;
    }
    public int getTotalSize() {
        return totalSize;
    }

    public  void remove(UploadFile uploadFile){
        if (this.contains(uploadFile)) {
            super.remove(uploadFile);
            super.remove(1);
            this.totalSize-=uploadFile.getSize();
        }
    }
    @Override
    public  UploadFile remove(int index){
        if (index>=0&&index<size()) {
            UploadFile uploadFile=this.get(index);
            remove(uploadFile);
            return uploadFile;
        }
        return  null;
    }
    public boolean add(UploadFile uploadFile){
        if(super.add(uploadFile)){
            this.totalSize+=uploadFile.getSize();
            return  true;
        }else{
            return  false;
        }
    }
    public boolean addAll(Collection<? extends UploadFile> uploadFiles){
        if(super.addAll(uploadFiles)){
            for(UploadFile uploadFile:uploadFiles){
                this.totalSize+=uploadFile.getSize();
            }
            return  true;
        }else{
            return  false;
        }
    }
}
