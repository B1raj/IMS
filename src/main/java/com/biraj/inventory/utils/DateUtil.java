package com.biraj.inventory.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
/**
 * @author birajmishra
 */
public class DateUtil {

	public static Date currentDate() {
		return new Date();
	}


	public static Timestamp getTimestamp() {
		Timestamp current = Timestamp.from(Instant.now());
		return current;
	}

}
