package com.quartz.demo.dao;

import com.quartz.demo.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 *
 * @author ：duc
 * @date ：Created in 2019/1/23 下午 02:53
 * @version: $version$
 */
public interface FileRepository extends JpaRepository<FileInfo, Integer>, JpaSpecificationExecutor<FileInfo>, Serializable{
}
