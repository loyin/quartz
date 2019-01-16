package com.quartz.demo.dao;

import com.quartz.demo.entity.JobLogs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface JobLogsRepository extends ElasticsearchRepository<JobLogs, String> {

}
