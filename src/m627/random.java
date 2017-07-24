package m627;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class random {

	private static  Map<String,String> getFixLenthString(Map<String,String> map) {  
	      
	    Random rm = new Random();  
	      
	    // 获得随机数  
	    Long pross =(long)((Math.random()*9+1)*10000000000l);  
	  
	    // 将获得的获得随机数转化为字符串  
	    String fixLenthString = "MZ"+String.valueOf(pross);  
	    map.put(fixLenthString, "秒账，帮您轻松做生意");
	    
	    // 返回固定的长度的随机数  
	    if(map.size()<300){
	    	getFixLenthString(map);
	    }
	    return map;
	}  
	public static void main(String[] args) {
		
		
		Map<String,String> map = getFixLenthString(new HashMap<String,String>());
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println(entry.getKey());
		}
	}
}
