package com.fhr.androiduploader.core;

import com.fhr.androiduploader.core.imgcompress.ImageCompress;
import com.fhr.androiduploader.core.picker.Picker;
import com.fhr.androiduploader.core.uploadengine.UploadEngine;
import com.fhr.androiduploader.events.UploaderListenerSet;
import com.fhr.androiduploader.events.eventobjects.BeforeFileQueuedEvent;
import com.fhr.androiduploader.events.eventobjects.ErrorEvent;
import com.fhr.androiduploader.events.eventobjects.FileDequeuedEvent;
import com.fhr.androiduploader.events.eventobjects.FileQueuedEvent;
import com.fhr.androiduploader.events.eventobjects.FilesQueuedEvent;
import com.fhr.androiduploader.events.eventobjects.ResetEvent;
import com.fhr.androiduploader.events.eventobjects.StartUploadEvent;
import com.fhr.androiduploader.events.eventobjects.StopUploadEvent;
import com.fhr.androiduploader.events.eventobjects.UploadCompleteEvent;
import com.fhr.androiduploader.events.eventobjects.UploadErrorEvent;
import com.fhr.androiduploader.events.eventobjects.UploadFinishedEvent;
import com.fhr.androiduploader.events.eventobjects.UploadProgressEvent;
import com.fhr.androiduploader.events.eventobjects.UploadStartEvent;
import com.fhr.androiduploader.events.eventobjects.UploadSuccessEvent;
import com.fhr.androiduploader.events.listeners.BeforeFileQueuedListener;
import com.fhr.androiduploader.events.listeners.ErrorListener;
import com.fhr.androiduploader.events.listeners.FileDequeuedListener;
import com.fhr.androiduploader.events.listeners.FileQueuedListener;
import com.fhr.androiduploader.events.listeners.FilesQueuedListener;
import com.fhr.androiduploader.events.listeners.ResetListener;
import com.fhr.androiduploader.events.listeners.StartUploadListener;
import com.fhr.androiduploader.events.listeners.StopUploadListener;
import com.fhr.androiduploader.events.listeners.UploadCompleteListener;
import com.fhr.androiduploader.events.listeners.UploadErrorListener;
import com.fhr.androiduploader.events.listeners.UploadFinishedListener;
import com.fhr.androiduploader.events.listeners.UploadProgressListener;
import com.fhr.androiduploader.events.listeners.UploadStartListener;
import com.fhr.androiduploader.events.listeners.UploadSuccessListener;
import com.fhr.androiduploader.models.FileList;
import com.fhr.androiduploader.models.UploadFile;
import com.fhr.androiduploader.types.FileStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * 上传并发线程数
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
     * 文件选择按钮集合
     */
    private final List<Picker> pickers=new ArrayList<>();
    /**
     * 图片压缩组件
     */
    private final ImageCompress imageCompress=null;
    /**
     * 上传组件
     */
    private  final UploadEngine uploadEngine=null;
    /**
     * 上传表单数据
     */
    private Map<String,Object> formData=null;
    /**
     * constructor
     * @param server
     * @param chunked
     * @param chunkedSize
     * @param thread
     * @param fileNumLimit
     * @param fileSizeLimit
     * @param fileSingleSizeLimit
     */
    public AndroidUploader(String server, boolean chunked, int chunkedSize, int thread, int fileNumLimit, int fileSizeLimit, int fileSingleSizeLimit, Picker picker,Map<String,Object> formData) {
        this.server = server;
        this.chunked = chunked;
        this.chunkedSize = chunkedSize;
        this.thread = thread;
        this.fileNumLimit = fileNumLimit;
        this.fileSizeLimit = fileSizeLimit;
        this.fileSingleSizeLimit = fileSingleSizeLimit;
        this.pickers.add(picker);
        this.formData=formData;
        this.fileList=new FileList(this);
    }
    /**
     * 开始上传
     */
    public  void upload(){
        //激发上传事件
        fireStartUpload(new StartUploadEvent(this,this.fileList.size(),this.fileList.getTotalSize()));
    }

    /**
     * 添加文件
     * @param file
     */
    public synchronized void addFile(File file){
        int fileCount=this.fileList.size();
        UploadFile uploadFile=new UploadFile(String.format("file-%d",fileCount+1),file.getPath(),file.getName(),file.length());
        //触发入队前事件
        this.fireBeforeFileQueued(new BeforeFileQueuedEvent(this,uploadFile));
        this.fileList.add(uploadFile);
        //触发入队后事件
        this.fireFileQueued(new FileQueuedEvent(this,uploadFile));
        uploadFile.setFileStatus(FileStatus.QUEUED);
    }

    /**
     * 移除文件
     * @param fileId
     */
    public  void removeFile(String fileId){
        UploadFile uploadFile=this.fileList.removeByFileId(fileId);
        if(uploadFile!=null){
            uploadFile.setFileStatus(FileStatus.CANCELLED);
            this.fireFileDequeued(new FileDequeuedEvent(this,uploadFile));
        }
    }

    public void reset(){

    }

    /***************************事件监听器集合**************************************/
    /**
     * 文件入队前的监听器集合
     */
    private final UploaderListenerSet<BeforeFileQueuedListener> beforeFileQueuedListeners=new UploaderListenerSet<>();
    /**
     * 错误事件监听器集合
     */
    private final UploaderListenerSet<ErrorListener> errorListeners=new UploaderListenerSet<>();
    /**
     * 文件移出队列监听器集合
     */
    private final  UploaderListenerSet<FileDequeuedListener>  fileDequeuedListeners=new UploaderListenerSet<>();
    /**
     * 文件入队后的监听器集合
     */
    private final  UploaderListenerSet<FileQueuedListener> fileQueuedListeners=new UploaderListenerSet<>();
    /**
     * 多文件加入队列后的监听器集合
     */
    private final  UploaderListenerSet<FilesQueuedListener> filesQueuedListeners=new UploaderListenerSet<>();
    /**
     * 重置uploader监听器集合
     */
    private final  UploaderListenerSet<ResetListener> resetListeners=new UploaderListenerSet<>();
    /**
     * 单文件开始上传监听器集合
     */
    private final  UploaderListenerSet<StartUploadListener> startUploadListeners=new UploaderListenerSet<>();
    /**
     * 单文件停止上传监听器集合
     */
    private final  UploaderListenerSet<StopUploadListener> stopUploadListeners=new UploaderListenerSet<>();
    /**
     *单文件上传完成监听级集合
     */
    private final  UploaderListenerSet<UploadCompleteListener> uploadCompleteListeners=new UploaderListenerSet<>();
    /**
     * 单文件上传出错监听器集合
     */
    private final  UploaderListenerSet<UploadErrorListener> uploadErrorListeners=new UploaderListenerSet<>();
    /**
     * 文件上传进度改变监听器集合
     */
    private final  UploaderListenerSet<UploadProgressListener> uploadProgressListeners=new UploaderListenerSet<>();
    /**
     * uploader上传结束监听器集合
     */
    private final  UploaderListenerSet<UploadFinishedListener> uploadFinishedListeners=new UploaderListenerSet<>();
    /**
     * uploader开始上传监听器集合
     */
    private final UploaderListenerSet<UploadStartListener> uploadStartListeners=new UploaderListenerSet<>();
    /**
     * 上传成功事件监听器集合
     */
    private final UploaderListenerSet<UploadSuccessListener> uploadSuccessListeners=new UploaderListenerSet<>();

    /********************监听器挂接卸载处理************************/

    public void addBeforeFileQueuedListener(BeforeFileQueuedListener beforeFileQueuedListener){
        this.beforeFileQueuedListeners.add(beforeFileQueuedListener);
    }
    public  void removeBeforeFileQueuedListener(BeforeFileQueuedListener beforeFileQueuedListener){
        this.beforeFileQueuedListeners.remove(beforeFileQueuedListener);
    }

    public  void addErrorListener(ErrorListener errorListener){
        this.errorListeners.add(errorListener);
    }
    public  void removeErrorListener(ErrorListener errorListener){
        this.errorListeners.remove(errorListener);
    }
    public  void addFileDequeuedListener(FileDequeuedListener fileDequeuedListener){
        this.fileDequeuedListeners.add(fileDequeuedListener);
    }

    public  void  removeFileDequeuedListener(FileQueuedListener fileQueuedListener){
        this.fileDequeuedListeners.remove(fileQueuedListener);
    }
    public  void addFileQueuedListener(FileQueuedListener fileQueuedListener){
        this.fileQueuedListeners.add(fileQueuedListener);
    }
    public  void removeFileQueuedListener(FileQueuedListener fileQueuedListener){
        this.fileQueuedListeners.remove(fileQueuedListener);
    }
    public  void addFilesQueuedListener(FilesQueuedListener filesQueuedListener){
        this.filesQueuedListeners.add(filesQueuedListener);
    }
    public  void removeFilesQueuedListener(FilesQueuedListener filesQueuedListener){
        this.filesQueuedListeners.remove(filesQueuedListener);
    }
    public  void  addResetListener(ResetListener resetListener){
        this.resetListeners.add(resetListener);
    }

    public  void  removeResetListener(ResetListener resetListener){
        this.resetListeners.remove(resetListener);
    }

    public  void addStartUploadListener(StartUploadListener startUploadListener){
        this.startUploadListeners.add(startUploadListener);
    }

    public  void removeStartUploadListener(StartUploadListener startUploadListener){
        this.startUploadListeners.remove(startUploadListener);
    }
    public  void addStopUploadListeners(StopUploadListener stopUploadListener){
        this.stopUploadListeners.add(stopUploadListener);
    }
    public  void removeStopUploadListeners(StopUploadListener stopUploadListener){
        this.stopUploadListeners.remove(stopUploadListener);
    }
    public  void addUploadCompleteListener(UploadCompleteListener uploadCompleteListener){
        this.uploadCompleteListeners.add(uploadCompleteListener);
    }
    public  void removeUploadCompleteListener(UploadCompleteListener uploadCompleteListener){
        this.uploadCompleteListeners.remove(uploadCompleteListener);
    }
    public  void addUploadErrorListener(UploadErrorListener uploadErrorListener){
        this.uploadErrorListeners.add(uploadErrorListener);
    }
    public  void reomoveUploadErrorListener(UploadErrorListener uploadErrorListener){
        this.uploadErrorListeners.remove(uploadErrorListener);
    }

    public  void  addUploadProgressListener(UploadProgressListener uploadProgressListener){
        this.uploadProgressListeners.add(uploadProgressListener);
    }
    public  void  removeUploadProgressListener(UploadProgressListener uploadProgressListener){
        this.uploadProgressListeners.remove(uploadProgressListener);
    }

    public  void addUploadFinishedListener(UploadFinishedListener uploadFinishedListener){
        this.uploadFinishedListeners.add(uploadFinishedListener);
    }

    public  void removeUploadFinishedListener(UploadFinishedListener uploadFinishedListener){
        this.uploadFinishedListeners.remove(uploadFinishedListener);
    }

    public  void addUploadStartListener(UploadStartListener uploadStartListener){
        this.uploadStartListeners.add(uploadStartListener);
    }
    public  void removeUploadStartListener(UploadStartListener uploadStartListener){
        this.uploadStartListeners.remove(uploadStartListener);
    }

    public  void addUploadSuccessListener(UploadSuccessListener successListener){
        this.uploadSuccessListeners.add(successListener);
    }
    public  void removeUploadSuccessrListener(UploadSuccessListener successListener){
        this.uploadSuccessListeners.remove(successListener);
    }
    /*********************事件触发**********************/

    public void fireBeforeFileQueued(BeforeFileQueuedEvent beforeFileQueuedEvent){
        this.beforeFileQueuedListeners.notifyListener(beforeFileQueuedEvent);
    }
    public  void fireError(ErrorEvent errorEvent){
        this.errorListeners.notifyListener(errorEvent);
    }
    public  void fireFileDequeued(FileDequeuedEvent fileDequeuedEvent){
        this.fileDequeuedListeners.notifyListener(fileDequeuedEvent);
    }
    public void  fireFileQueued(FileQueuedEvent fileQueuedEvent){
        this.filesQueuedListeners.notifyListener(fileQueuedEvent);
    }
    public  void fireFilesQueued(FilesQueuedEvent filesQueuedEvent){
        this.filesQueuedListeners.notifyListener(filesQueuedEvent);
    }
    public void fireReset(ResetEvent resetEvent){
        this.resetListeners.notifyListener(resetEvent);
    }
    public void fireStartUpload(StartUploadEvent startUploadEvent){
        this.stopUploadListeners.notifyListener(startUploadEvent);
    }
    public void fireStopUpload(StopUploadEvent stopUploadEvent){
        this.stopUploadListeners.notifyListener(stopUploadEvent);
    }
    public void fireUploadComplete(UploadCompleteEvent uploadCompleteEvent){
        this.uploadCompleteListeners.notifyListener(uploadCompleteEvent);
    }
    public void fireUploadError(UploadErrorEvent uploadErrorEvent){
        this.uploadErrorListeners.notifyListener(uploadErrorEvent);
    }
    public  void fireUploadProgress(UploadProgressEvent uploadProgressEvent){
        this.uploadProgressListeners.notifyListener(uploadProgressEvent);
    }
    public  void fireUploadFinished(UploadFinishedEvent uploadFinishedEvent){
        this.uploadFinishedListeners.notifyListener(uploadFinishedEvent);
    }
    public void fireUploadStart(UploadStartEvent uploadStartEvent){
        this.uploadStartListeners.notifyListener(uploadStartEvent);
    }
    public  void fireUploadSuccess(UploadSuccessEvent uploadSuccessEvent){
        this.uploadSuccessListeners.notifyListener(uploadSuccessEvent);
    }


    public String getServer() {
        return server;
    }

    public boolean getChunked() {
        return chunked;
    }

    public int getChunkedSize() {
        return chunkedSize;
    }

    public int getThread() {
        return thread;
    }
    public FileList getFileList(){
        return  this.fileList;
    }

    public Map<String, Object> getFormData() {
        return formData;
    }
}
