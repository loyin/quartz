package com.quartz.demo.service;

import com.quartz.demo.dao.FileRepository;
import com.quartz.demo.entity.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：duc
 * @date ：Created in 2019/1/23 下午 02:55
 * @version: $version$
 */
@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public List<FileInfo> findAll() {
        return fileRepository.findAll();
    }

    public FileInfo save(String s) {
        FileInfo fileInfo = new FileInfo();
        return fileInfo;
    }
}
