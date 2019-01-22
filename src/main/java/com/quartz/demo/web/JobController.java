package com.quartz.demo.web;

import com.quartz.demo.entity.JobInfo;
import com.quartz.demo.service.JobService;
import com.quartz.demo.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(){
        List<JobInfo> list = jobService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        map.put("code", Constants.LAYUI_TABLE_SUCCESS_CODE);
        return map;
    }

    @RequestMapping("/add/{id}")
    public ModelAndView add(@PathVariable Integer id){
        return new ModelAndView("/quartz/add", "jobInfo", id > 0 ? jobService.findById(id) : new JobInfo());
    }

    @RequestMapping("/addSave")
    @ResponseBody
    public String addSave(JobInfo jobInfo) throws Exception {
        return jobInfo.getId() != null && jobInfo.getId() > 0 ? jobService.edit(jobInfo) : jobService.insert(jobInfo);
    }

    @RequestMapping("/init/{id}")
    @ResponseBody
    public String init(@PathVariable Integer id){
        return jobService.initJob(id);
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id){
        return jobService.deleteJobById(id);
    }

    @RequestMapping("/start/{id}")
    @ResponseBody
    public String start(@PathVariable Integer id){
        return jobService.startJob(id);
    }

    @RequestMapping("/stop/{id}")
    @ResponseBody
    public String stop(@PathVariable Integer id){
        return jobService.stopJob(id);
    }
}
