package com.fhr.androiduploader.events;

import java.util.HashSet;

/**
 * Uploader事件监听器泛型集合
 * Created by fhr on 2017/6/2.
 */

public class UploaderListenerSet<T extends UploaderListener> extends HashSet<T> {

    public  void notifyListener(final UploaderEvent event){
        if(event!=null) {
            for (T listener:this){
                listener.HandleEvent(event);
            }
        }
    }

}
