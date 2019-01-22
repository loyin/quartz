package com.quartz.demo.dao;

import com.quartz.demo.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface JobRepository extends JpaRepository<JobInfo, Integer>, JpaSpecificationExecutor<JobInfo>, Serializable {

    /**
     * 用name与groupName查询Job对象
     * @param name
     * @param groupName
     */
    JobInfo findByNameAndGroupName(String name, String groupName);

}
