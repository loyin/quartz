package com.quartz.demo.Job.JobManager;

import com.quartz.demo.base.util.Constants;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("QuartzUtils")
public class QuartzManager {

    private Logger log = LoggerFactory.getLogger(QuartzManager.class);

    @Autowired
    private Scheduler scheduler;

    /**
     * 修改任务Cron
     */
    public void modifyTigger(String name, String group, String cron)throws SchedulerException {
        TriggerKey key = TriggerKey.triggerKey(name, group);
        Trigger.TriggerState triggerState = scheduler.getTriggerState(key);
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(key).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        scheduler.rescheduleJob(key, trigger);
        JobKey k =new JobKey(name, group);
        log.info("修改任务:{}", name);
        if(!Constants.NORMAL.equals(triggerState.name())){
            scheduler.pauseJob(k);
        }
    }

    /**
     * 添加Job
     * @param jobName job名称
     * @param cls    job执行类
     * @param group  群组名称
     * @param cron    cron表达式
     */
    public void addJob(String jobName, Class cls, String group, String cron)throws SchedulerException {
        // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
        JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, group).build();
        // 构建一个触发器，规定触发的规则
        Trigger trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
                .withIdentity(jobName, group)// 给触发器起一个名字和组名
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))// 触发器的执行时间
                .build();// 产生触发器
        // 调度Job
        scheduler.scheduleJob(jobDetail, trigger);
        log.debug("添加任务:{},{},{}", jobName, cls, cron);
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 暂停一个任务
     */
    public void pauseJob(String jobName, String jobGroupName)throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.pauseJob(jobKey);
        log.info("暂停任务:{}", jobName);
    }

    /**
     * 恢复一个任务
     */
    public void resumeJob(String jobName, String jobGroupName)throws Exception {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        scheduler.resumeJob(jobKey);
        log.info("恢复任务:{}", jobName);
    }

    /**
     * 移除一个任务
     */
    public void removeJob(String jobName, String group)throws SchedulerException {
        JobKey jobKey =new JobKey(jobName, group);
        // 停止触发器
        scheduler.pauseJob(jobKey);
        scheduler.unscheduleJob(new TriggerKey(jobName, group));// 移除触发器
        scheduler.deleteJob(jobKey);// 删除任务
        log.info("移除任务:{}", jobName);
    }

    /**
     * 启动所有定时任务
     */
    public void startJobs() {
        try {
            scheduler.start();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
