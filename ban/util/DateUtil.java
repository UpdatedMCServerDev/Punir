package ban.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S");
	
	public static String format(long date) {
		return format.format(date);
	}
	
	public static Date parse(long date) {
		try {
			return format.parse(date + "");
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean canJoin(long end) {
		return parse(end).after(new Date());
	}

}
