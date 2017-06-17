package com.fhr.androiduploader.core.imgcompress;

/**
 * 压缩参数
 * Created by fhr on 2017/6/4.
 */

public class CompressParam {

    private final int width;
    private final int height;
    // 图片质量，只有type为`image/jpeg`的时候才有效。
    private final int quality;

    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
    private final boolean allowMagnify;

    // 是否允许裁剪。
    private final  boolean crop;

    // 是否保留头部meta信息。
    private final  boolean preserveHeaders;

    // 如果发现压缩后文件大小比原来还大，则使用原来图片
    // 此属性可能会影响图片自动纠正功能
    private final boolean noCompressIfLarger;

    // 单位字节，如果图片大小小于此值，不会采用压缩。
    private final boolean compressSize;

    public CompressParam(int width, int height, int quality, boolean allowMagnify, boolean crop, boolean preserveHeaders, boolean noCompressIfLarger, boolean compressSize) {
        this.width = width;
        this.height = height;
        this.quality = quality;
        this.allowMagnify = allowMagnify;
        this.crop = crop;
        this.preserveHeaders = preserveHeaders;
        this.noCompressIfLarger = noCompressIfLarger;
        this.compressSize = compressSize;
    }
}
