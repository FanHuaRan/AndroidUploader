package fhr.com.androiduploader.types;

/**
 * uploader总的错误类型
 * Created by fhr on 2017/6/2.
 */

public enum ErrorType {
    /**
     * 文件数量过大
     */
    Q_EXCEED_NUM_LIMIT,
    /**
     * 文件总尺寸过大
     */
    Q_EXCEED_SIZE_LIMIT,
    /**
     * 文件类型不满足
     */
    Q_TYPE_DENIED
}
