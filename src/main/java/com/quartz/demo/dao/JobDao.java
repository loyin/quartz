package com.quartz.demo.dao;

import com.quartz.demo.entity.JobInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface JobDao extends JpaRepository<JobInfo, Integer>, JpaSpecificationExecutor<JobInfo>, Serializable {

    JobInfo findByNameAndGroupName(String name, String groupName);

}
