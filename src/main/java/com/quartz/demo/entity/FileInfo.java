package com.quartz.demo.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 附件
 * @author ：duc
 * @date ：Created in 2019/1/23 下午 02:40
 * @version: $version$
 */
@Entity
@Table(name = "file_info")
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 附件名称
     */
    private String fileName;
    /**
     * 附件唯一标识 mongodb中的key
     */
    private String fileJson;

    private Date createDate;

    private String createUser;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileJson() {
        return fileJson;
    }

    public void setFileJson(String fileJson) {
        this.fileJson = fileJson;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
