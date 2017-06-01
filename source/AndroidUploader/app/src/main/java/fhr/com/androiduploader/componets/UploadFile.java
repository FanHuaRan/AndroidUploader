package fhr.com.androiduploader.componets;

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
    private final  int size;

    /**
     * constructor
     */
    public UploadFile(String fileId, String filePath, String name, int size) {
        this.fileId = fileId;
        this.filePath = filePath;
        this.name = name;
        this.size = size;
    }
}
