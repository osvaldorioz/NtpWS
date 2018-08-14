package mx.org.ift.timejob;

import mx.org.ift.ntp.util.WebBot;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class ElapseTime implements Job{
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		WebBot.timeElapsed += 1000;
	}
}
