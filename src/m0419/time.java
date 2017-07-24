package m0419;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class time {
	public static void main(String[] args) throws ParseException {
		// Calendar c = Calendar.getInstance();//获得一个日历的实例
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// c.setTime(new Date());//设置日历时间
		// c.add(Calendar.MONTH,-6);//在日历的月份上减少6个月
		// System.out.println(sdf.format(c.getTime()));//得到6个月前的日期

//		String a = "0.000";
//		for (int i = a.length() - 1; i >= 0; i--) {
//
//			if (String.valueOf(a.charAt(i)).equals("0") && a.contains(".")) {
//				a = a.substring(0, i);
//			} else {
//				if (String.valueOf(a.charAt(i)).equals(".")) {
//					if (a.length() > i) {
//						a = a.substring(0, i);
//					}
//					break;
//				} else {
//					break;
//				}
//			}
//		}
//		System.out.print(a);
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		String a = "2017-05-13";
////		Date d= sdf.parse(a);
//		System.out.println(a.compareTo(sdf.format(new Date())));
		
		 String dateStr = "2015-1-3 ";
	        String dateStr2 = "2015-1-2 ";
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
	        try 
	        {
	            Date date2 = format.parse(dateStr2);
	            Date date = format.parse(dateStr);
	            
//	            System.out.println("两个日期的差距：" + differentDays(date,date2));
	            System.out.println("两个日期的差距：" + differentDaysByMillisecond(date,date2));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	}
	 public static int differentDaysByMillisecond(Date date1,Date date2)
	    {
	        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
	        return days;
	    }
}
