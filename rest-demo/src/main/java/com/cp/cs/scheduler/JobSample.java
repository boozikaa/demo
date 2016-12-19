package com.cp.cs.scheduler;

import java.util.Date;

import javax.transaction.Transactional;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cp.cs.repository.CustomerRepository;
import com.cp.cs.util.StaticContextHolder;

public class JobSample implements Job {

	private final static Logger LOGGER = LoggerFactory.getLogger(JobSample.class);

	private CustomerRepository customerRepository = StaticContextHolder.getBean(CustomerRepository.class);

	@Transactional
	public void execute(JobExecutionContext context) throws JobExecutionException {
		LOGGER.info("execute SynchronizeTaskJob on {}", new Date());
		LOGGER.info("Customer: {}", customerRepository.getAll().get(0).getId());
	}

}
