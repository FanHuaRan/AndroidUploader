package fhr.com.androiduploader.core;

import java.util.ArrayList;
import java.util.List;

import fhr.com.androiduploader.core.chunker.Chunker;
import fhr.com.androiduploader.core.imgcompress.ImageCompress;
import fhr.com.androiduploader.core.picker.Picker;
import fhr.com.androiduploader.core.uploadengine.UploadEngine;
import fhr.com.androiduploader.events.UploaderListenerSet;
import fhr.com.androiduploader.events.listeners.ErrorListener;
import fhr.com.androiduploader.events.listeners.UploadSuccessListener;
import fhr.com.androiduploader.models.FileList;

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
     * 文件集合对象
     */
    private final FileList fileList;
    /***************************组件********************************************/
    /**
     * 主要的文件选择按钮
     */
    private Picker picker;
    /**
     * 更多的文件选择按钮
     */
    private final List<Picker> button=new ArrayList<>();
    /**
     * 分片组件
     */
    private final Chunker chunker=null;
    /**
     * 图片压缩组件
     */
    private final ImageCompress imageCompress=null;
    /**
     * 上传组件
     */
    private  final UploadEngine uploadEngine=null;
    /***************************事件监听器**************************************/
    /**
     * 错误事件监听器集合
     */
    private final UploaderListenerSet<ErrorListener> errorListeners=new UploaderListenerSet<>();
    /**
     * 上传成功事件监听器集合
     */
    private final UploaderListenerSet<UploadSuccessListener> uploadSuccessListeners=new UploaderListenerSet<>();
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
        this.fileList=new FileList(this);
    }

    /**
     * 工厂方法进行创建
     * @return
     */
    public  static  AndroidUploader createUploader() {
        return  null;
    }
    /********************监听器挂接卸载处理************************/
    public  void addErrorListener(ErrorListener errorListener){
        this.errorListeners.add(errorListener);
    }
    public  void removeErrorListener(ErrorListener errorListener){
        this.errorListeners.remove(errorListener);
    }
    public  void addUploadSuccessListener(UploadSuccessListener successListener){
        this.uploadSuccessListeners.add(successListener);
    }
    public  void removeUploadSuccessrListener(UploadSuccessListener successListener){
        this.uploadSuccessListeners.remove(successListener);
    }
}
