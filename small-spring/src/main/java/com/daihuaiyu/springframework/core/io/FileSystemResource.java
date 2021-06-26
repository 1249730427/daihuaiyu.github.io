package com.daihuaiyu.springframework.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 本地配置文件加载器
 * @Author: daihuaiyu
 * @Date: 2021/6/26 08:28
 * @Description:用于加载本地的Spring配置文件
 */
public class FileSystemResource implements Resource {

    private final String filePath;

    private final File file;

    public FileSystemResource(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public FileSystemResource(File file) {
        this.filePath = file.getPath();
        this.file = file;
    }

    /**
     * 获取IO输入流
     *
     * @return
     * @throws IOException
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }
}
