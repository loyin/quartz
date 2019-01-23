package com.quartz.demo.web;

import com.mongodb.gridfs.GridFSFile;
import com.quartz.demo.base.util.Constants;
import com.quartz.demo.entity.FileInfo;
import com.quartz.demo.service.FileService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：duc
 * @date ：Created in 2019/1/23 下午 02:55
 * @version: $version$
 */
@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @RequestMapping("/list")
    public String list(){
        return "fileUpload/fileList";
    }

    @RequestMapping("/list/data")
    public @ResponseBody Map<String, Object> listData(){
        List<FileInfo> list = fileService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("code", Constants.LAYUI_TABLE_SUCCESS_CODE);
        return map;
    }

    @RequestMapping("/addFile")
    public String addFile(){
        return "fileUpload/addFile";
    }

    @RequestMapping("/save/file")
    public @ResponseBody String saveFile(@RequestParam("file") MultipartFile file){
        try {
            ObjectId objectId = gridFsTemplate.store(file.getInputStream(), file.getName(), file.getContentType());
            fileService.save(objectId.toString());
            return "true";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
