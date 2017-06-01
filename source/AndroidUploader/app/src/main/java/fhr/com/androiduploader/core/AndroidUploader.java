package fhr.com.androiduploader.core;

import java.util.ArrayList;
import java.util.List;

import fhr.com.androiduploader.componets.Picker;

/**
 * 上传控件核心 工厂模式进行创建
 * Created by fhr on 2017/6/1.
 */

public class AndroidUploader {
    /**
     * 上传地址
     */
    private final  String server;
    /**
     * 是否分片上传
     */
    private final boolean chunked;
    /**
     * 分片文件大小
     */
    private final int  chunkedSize;
    /**
     * 总文件最大尺寸限制 单位byte
     */
    private final  int thread;
    /**
     * 总文件最大尺寸限制 单位byte
     */
    private final int fileNumLimit;
    /**
     * 总文件最大尺寸限制 单位byte
     */
    private final  int fileSizeLimit;
    /**
     * 单文件尺寸最大限制 单位byte
     */
    private final  int fileSingleSizeLimit;
    /**
     * 文件选择按钮
     */
    private Picker picker;
    /**
     * 附件的文件选择按钮
     */
    private final List<Picker> button=new ArrayList<>();

    /**
     * constructor
     * @param server
     * @param chunked
     * @param chunkedSize
     * @param thread
     * @param fileNumLimit
     * @param fileSizeLimit
     * @param fileSingleSizeLimit
     * @param picker
     */
    private AndroidUploader(String server, boolean chunked, int chunkedSize, int thread, int fileNumLimit, int fileSizeLimit, int fileSingleSizeLimit, Picker picker) {
        this.server = server;
        this.chunked = chunked;
        this.chunkedSize = chunkedSize;
        this.thread = thread;
        this.fileNumLimit = fileNumLimit;
        this.fileSizeLimit = fileSizeLimit;
        this.fileSingleSizeLimit = fileSingleSizeLimit;
        this.picker = picker;
    }

    /**
     * 工厂方法进行创建
     * @return
     */
    public  static  AndroidUploader createUploader() {
        return  null;
    }

}
