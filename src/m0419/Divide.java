package m0419;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Divide {
	public static void main(String[] args) {
//		int a = 1;
//		int b = a /12;
//		int c = a%12;
//		System.out.println("b:"+b);
//		System.out.println("c:"+c);
//		String aa = "һ�����������߰˾�ʮ";
//		String str = getNewLine(aa,8);
//		System.out.print(str);
		 final int j = 0;
		for ( int i = 0 ; i<3; i++) {

			Integer cacheTime = 1000 * 3;  
	        Timer timer = new Timer();  
	        // (TimerTask task, long delay, long period)任务，延迟时间，多久执行  
	        timer.schedule(new TimerTask() {  
	  
	            @Override  
	            public void run() {  
	                System.out.println(new Date());  
	            }  
	        }, 1000, cacheTime);  
		}
	}

	public static String getNewLine(String initStr,int len) {
		
		String str = "";
		int length = initStr.length();
		int count = length / len;
		for(int i = 1 ; i < count+1 ; i++){
			if(i==1){
				str = initStr.substring(0, i*len)+"\n";
				if(count == 1){
					str += initStr.substring(i*len, length);
				}
			}else{
				str += initStr.substring((i-1)*len, i*len)+"\n";
			}
		}
		return str;
	}
}
