package fhr.com.androiduploader.events;

import java.util.EventListener;

/**
 * Uploader事件监听器泛型基接口
 * Created by fhr on 2017/6/2.
 */

public interface UploaderListener<T extends UploaderEvent> extends EventListener {
    void HandleEvent(T event);
}
