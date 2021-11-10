package com.amateur.io;

import java.io.InputStream;

/**
 * @author yeyu
 * @date 2021/11/10 17:40
 */
public class Resource {
    /**
     * 根据配置文件路径 将配置文件加载成字节输入流存储在内存中
     * @param path 文件路径
     * @return InputStream流
     */
    public static InputStream getResourceAsStream(String path){
        return Resource.class.getClassLoader().getResourceAsStream(path);
    }
}
