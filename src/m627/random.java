package m627;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class random {

	private static  Map<String,String> getFixLenthString(Map<String,String> map) {  
	      
	    Random rm = new Random();  
	      
	    // ��������  
	    Long pross =(long)((Math.random()*9+1)*10000000000l);  
	  
	    // ����õĻ�������ת��Ϊ�ַ���  
	    String fixLenthString = "MZ"+String.valueOf(pross);  
	    map.put(fixLenthString, "���ˣ���������������");
	    
	    // ���ع̶��ĳ��ȵ������  
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
