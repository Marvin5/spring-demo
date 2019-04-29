package com.example.demo.job.quartz;

import com.example.demo.core.service.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class SampleJob extends QuartzJobBean {
  private EchoService echoService;
  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    // log.info("test");
  }

  public void setEchoService(EchoService echoService) {
    this.echoService = echoService;
  }
}
