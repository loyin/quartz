package com.quartz.demo.web;

import com.quartz.demo.service.JobLogsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jobLogs")
public class JobLogsController {

    private static final Logger log = LoggerFactory.getLogger(JobLogsController.class);

    @Autowired
    private JobLogsService jobLogsService;

    @RequestMapping("/findByJob/{id}")
    public ModelAndView findByJob(@PathVariable String id){
        jobLogsService.findByJobId(id);
        return new ModelAndView();
    }
}
