package com.cp.cs.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

@Service
@PropertySources(value = { @PropertySource("classpath:application.properties") })
public class TaskSchedulerJob {

	private final static Logger LOGGER = LoggerFactory.getLogger(TaskSchedulerJob.class);

	public TaskSchedulerJob() {

		LOGGER.info("Scheduler....");
		try {

				JobDetail jobDetail = newJob(JobSample.class).withIdentity("SYNC_JOB").build();
				CronTrigger cronTrigger = newTrigger().withIdentity("SYNC_TRIGGER").startAt(new Date()).withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")).build();

				Scheduler scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(jobDetail, cronTrigger);
				LOGGER.info("Scheduler.... starting");
				LOGGER.info("Scheduler.... started");
				
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
