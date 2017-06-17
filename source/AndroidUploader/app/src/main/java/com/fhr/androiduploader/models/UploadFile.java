package com.fhr.androiduploader.models;

import com.fhr.androiduploader.types.FileStatus;

/**
 * 上传文件
 * Created by fhr on 2017/6/1.
 */

public class UploadFile {
    /**
     * 文件编号
    */
    private final  String fileId;
    /**
     * 本地文件路径
     */
    private final  String filePath;
    /**
     * 文件名
     */
    private final  String name;
    /**
     * 文件大小 单位byte
     */
    private final  long size;
    /**
     * 文件状态
     */
    private FileStatus fileStatus;

    /**
     * constructor
     * @param fileId
     * @param filePath
     * @param name
     * @param size
     */
    public UploadFile(String fileId, String filePath, String name, long size) {
        this.fileId = fileId;
        this.filePath = filePath;
        this.name = name;
        this.size = size;
        this.fileStatus=FileStatus.INITED;
    }

    public String getFileId() {
        return fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public FileStatus getFileStatus() {return fileStatus;}

    public void setFileStatus(FileStatus fileStatus) {this.fileStatus = fileStatus;}
}
