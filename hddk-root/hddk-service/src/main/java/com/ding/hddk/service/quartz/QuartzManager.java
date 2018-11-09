package com.ding.hddk.service.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

public class QuartzManager {
    @Autowired
    private Scheduler scheduler;

    public static void addJob(Scheduler scheduler, Class<? extends Job> jobClass, String jobGroupName, String cronExpression, Object... objects) {
        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobClass.getName(), jobGroupName)
                .build();
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                jobDetail.getJobDataMap().put("data" + (i + 1), objects[i]);
            }
        }
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        Trigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail).withSchedule(scheduleBuilder)
                .withIdentity(jobClass.getName(), jobGroupName)
                .startNow()
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
