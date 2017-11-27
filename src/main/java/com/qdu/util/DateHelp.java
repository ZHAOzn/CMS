package com.qdu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelp {
	public static int getMinate(Date now,Date examTime) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		   now = df.parse(df.format(now));
		   examTime = df.parse(df.format(examTime));
		   long l=now.getTime()-examTime.getTime();
		   long day=l/(24*60*60*1000);
		   long hour=(l/(60*60*1000)-day*24);
		   long min=((l/(60*1000))-day*24*60);
		   return (int)min;
	}
	

}
