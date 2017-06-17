package com.fhr.androiduploader.core.picker;

import com.fhr.androiduploader.core.AndroidUploader;

/**
 * 文件选择按钮
 * Created by fhr on 2017/6/1.
 */

public class Picker {
    /**
     * 页面控件编号
     */
    private  final  String id;

    /**
     * 是否准许多选
     */
    private final  boolean multiple;
    /**
     * 页面控件文本
     */
    private String lable;
    /**
     * uploader 这儿是便于进行回调 这儿无法加final 唉
     */
    private  AndroidUploader uploader;

    /**
     * full constructor
     * @param id
     * @param multiple
     * @param lable
     */
    public  Picker(String id,boolean multiple,String lable){
        this.id=id;
        this.multiple=multiple;
        this.lable=lable;
    }

    /**
     * less constructor
     * @param id
     * @param lable
     */
    public  Picker(String id,String lable){
        this(id,true, lable);
    }
    /**
     * getters setters
     */
    public String getId() {
        return id;
    }

    public String getLable() {
        return lable;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public AndroidUploader getUploader() {
        return uploader;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public void setUploader(AndroidUploader uploader) {
        this.uploader = uploader;
    }
}
