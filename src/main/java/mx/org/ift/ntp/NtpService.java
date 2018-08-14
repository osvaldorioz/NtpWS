package mx.org.ift.ntp;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import mx.org.ift.timejob.ElapseTime;
import mx.org.ift.timejob.Timer;
import mx.org.ift.ntp.service.impl.NtpServiceImpl;
import mx.org.ift.ntp.util.TimeConverters;
import mx.org.ift.ntp.util.WebBot;
import static mx.org.ift.ntp.constantes.Constante.URL_TIME;

@Path("/ntptime")
public class NtpService {
	private Scheduler scheduler;
	private Scheduler scheduler2;

	@GET
	@Path("/cdt")
	@Produces(MediaType.TEXT_PLAIN)
	public String getTime(){
		Long time = WebBot.timeElapsed + new Random().nextInt(100);
		return TimeConverters.convertLong(WebBot.timeElapsed).toString();
	}
	
	@GET
	@Path("/update")
	@Produces(MediaType.TEXT_PLAIN)
	public String update(){
		String url = URL_TIME;
		
		Timestamp s = new NtpServiceImpl().getTime(url);
		WebBot.timeElapsed = s.getTime();
		return TimeConverters.convertLong(WebBot.timeElapsed).toString();
	}
	
	@GET
	@Path("/timer/{varX}")
	@Produces(MediaType.TEXT_HTML)
	public String startstop(@PathParam("varX") String varX){
		String msg = "";
		
		JobDetail job = JobBuilder.newJob(Timer.class)
				.withIdentity("Timer", "group1").build();
		
		JobDetail job2 = JobBuilder.newJob(ElapseTime.class)
				.withIdentity("Elapsed", "group1").build();

		Trigger trigger = TriggerBuilder
				.newTrigger()
				.withIdentity("Timer", "group1")
				.withSchedule(
			SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(24)
				.repeatForever())
				.build();
		
		Trigger trigger2 = TriggerBuilder
				.newTrigger()
				.withIdentity("Elapsed", "group1")
				.withSchedule(
			SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1)
				.repeatForever())
				.build();

		try{
			if(varX.equals("start")){
				scheduler = new StdSchedulerFactory().
						getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
				
				scheduler2 = new StdSchedulerFactory().
						getScheduler();
				scheduler2.start();
				scheduler2.scheduleJob(job2, trigger2);
				
				msg = "Timer iniciado";
			} else if(varX.equals("stop")){
				if(scheduler != null && !scheduler.isShutdown()){
					scheduler.shutdown();
					scheduler2.shutdown();
					msg = "Timer detenido";
				} else {
					msg = "Timer no está en ejecución";
				}
			}
		}catch(SchedulerException err){
			
		}
	
		return msg;
	}

}