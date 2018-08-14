package mx.org.ift.ntp.service;

import java.sql.Timestamp;

public interface NtpService {
	public Timestamp getTime(String url);
}
