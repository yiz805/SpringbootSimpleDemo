package com.ding.hddk.service.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloJob implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOGGER.info("执行Hello" + new Date());
    }
}
