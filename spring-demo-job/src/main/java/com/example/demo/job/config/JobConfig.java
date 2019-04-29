package com.example.demo.job.config;

import com.example.demo.job.quartz.SampleJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static org.quartz.CronScheduleBuilder.cronSchedule;

@Configuration
public class JobConfig {
  @Autowired private Scheduler scheduler;

  @PostConstruct
  public void schedule() throws Exception {
    JobDetail jobDetail =
        JobBuilder.newJob(SampleJob.class).withIdentity("job-1", "group-1").build();
    CronTrigger cronTrigger =
        TriggerBuilder.newTrigger()
            .withIdentity("trigger-1", "group-1")
            .withSchedule(cronSchedule("0/10 * * * * ?"))
            .build();
    scheduler.scheduleJob(jobDetail, cronTrigger);
  }
}
