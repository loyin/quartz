package com.quartz.demo.service;

import com.quartz.demo.dao.JobLogsRepository;
import com.quartz.demo.entity.JobInfo;
import com.quartz.demo.entity.JobLogs;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class JobLogsService {

    @Autowired
    private JobLogsRepository jobLogsRepository;

    public List<JobLogs> findByJobId(String id) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(id);
        Iterable<JobLogs> job = jobLogsRepository.search(builder);
        Iterator<JobLogs> jobLogs = job.iterator();
        List<JobLogs> list = new ArrayList<>();
        while (jobLogs.hasNext()){
            list.add(jobLogs.next());
        }
        return list;
    }

    public void save(JobInfo jobInfo) {
        JobLogs jobLogs = new JobLogs();
        jobLogs.setId("1");
        jobLogs.setJobId(jobInfo.getId().toString());
        jobLogs.setText("testetsetstestestes");
        jobLogs.setCreateDate(new Date());
        jobLogsRepository.save(jobLogs);
    }
}
