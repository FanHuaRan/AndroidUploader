package fhr.com.androiduploader.core;

import java.util.HashSet;

import fhr.com.androiduploader.events.UploaderEvent;
import fhr.com.androiduploader.events.UploaderListener;

/**
 * Uploader事件监听器泛型集合
 * Created by fhr on 2017/6/2.
 */

public class UploaderListenerSet<T extends UploaderListener> extends HashSet<T> {

    public  void NotifyListener(final UploaderEvent event){
        if(event!=null) {
            for (T listener:this){
                listener.HandleEvent(event);
            }
        }
    }

}
