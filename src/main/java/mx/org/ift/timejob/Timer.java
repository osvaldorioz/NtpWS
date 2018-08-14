package mx.org.ift.timejob;

import java.sql.Timestamp;

import mx.org.ift.ntp.service.impl.NtpServiceImpl;
import mx.org.ift.ntp.util.WebBot;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Timer implements Job{
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String url = "http://free.timeanddate.com/clock/i6cg2d0r/n162/szw110/szh110/hbw0/hfc000/cf100/hgr0/fav0/fiv0/mqcfff/mql15/mqw4/mqd94/mhcfff/mhl15/mhw4/mhd94/mmv0/hhcbbb/hmcddd/hsceee";
		
		Timestamp s = new NtpServiceImpl().getTime(url);
		WebBot.timeElapsed = s.getTime();
	}
}
