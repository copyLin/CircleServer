package test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StringToDateTest {

	public static void main(String[] args) {
		String sendTime = "2019-02-16 16:22:23";
		
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(sendTime);
		System.out.println("Date: " + date);
		
		TimeZone mTimeZone = TimeZone.getTimeZone("GMT+8");
        formatter.setTimeZone(mTimeZone);
        String BeijingTime = formatter.format(Calendar.getInstance().getTime());
        System.out.println("TimeZone: " + BeijingTime);
        
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
