package com.quartz.demo.service;

import com.quartz.demo.Job.JobManager.QuartzManager;
import com.quartz.demo.base.util.Constants;
import com.quartz.demo.dao.JobRepository;
import com.quartz.demo.entity.JobInfo;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobService{

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private QuartzManager quartzManager;

    @Autowired
    private JobLogsService jobLogsService;

    public List<JobInfo> findAll() {
        return jobRepository.findAll();
    }

    public String insert(JobInfo jobInfo) throws Exception {
        jobInfo.setStartTime(new Date());
        jobInfo.setStatus(Constants.NOSCHED);
        JobInfo job = jobRepository.findByNameAndGroupName(jobInfo.getName(), jobInfo.getGroupName());
        if(job != null) {
            throw new Exception("jobName与groupName不能与原有job相同!");
        }
        jobInfo = jobRepository.save(jobInfo);
        jobLogsService.save(jobInfo);
        return jobInfo.getId() > 0 ? "true" : "false";
    }

    public String initJob(Integer id){
        JobInfo jobInfo = findById(id);
        try {
            quartzManager.addJob(jobInfo.getName(), Class.forName(jobInfo.getClassName()), jobInfo.getGroupName(), jobInfo.getCron());
            jobInfo.setStatus(Constants.NORMAL);
            jobRepository.saveAndFlush(jobInfo);
            return "true";
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String edit(JobInfo jobInfo) {
        try {
            quartzManager.modifyTigger(jobInfo.getName(), jobInfo.getGroupName(), jobInfo.getCron());
            jobRepository.saveAndFlush(jobInfo);
            return "true";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String deleteJobById(Integer id) {
        JobInfo jobInfo = findById(id);
        try {
            quartzManager.removeJob(jobInfo.getName(), jobInfo.getGroupName());
            jobRepository.delete(jobInfo);
            return "true";
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String stopJob(Integer id) {
        JobInfo jobInfo = findById(id);
        try {
            quartzManager.pauseJob(jobInfo.getName(), jobInfo.getGroupName());
            jobInfo.setStatus(Constants.PAUSED);
            jobRepository.saveAndFlush(jobInfo);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public String startJob(Integer id) {
        JobInfo jobInfo = findById(id);
        try {
            quartzManager.resumeJob(jobInfo.getName(), jobInfo.getGroupName());
            jobInfo.setStatus(Constants.NORMAL);
            jobRepository.saveAndFlush(jobInfo);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    public JobInfo findById(Integer id){
        return jobRepository.findById(id).get();
    }
}
