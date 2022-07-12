package com.tansci.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName： ImageUtiles.java
 * @ClassPath： com.tansci.utils.ImageUtiles.java
 * @Description： 图片操作
 * @Author： tanyp
 * @Date： 2022/03/29 10:38
 **/
@Slf4j
public class ImageUtiles {

    /**
     * @methodName：getImageSrc
     * @description：获取网页上所有的图片路径 该方法排除video与audio标签中的img
     * @author：tanyp
     * @dateTime：2022/03/29 10:38
     * @Params： [html]
     * @Return： java.util.List<java.lang.String>
     * @editNote：
     */
    public static List<String> getImageSrc(String html) {
        if (Objects.isNull(html)) {
            return null;
        }
        List<String> imageSrcList = new ArrayList<>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            imageSrcList.add(src);
        }
        return imageSrcList;
    }

}
