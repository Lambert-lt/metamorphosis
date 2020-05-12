package m0627;

import java.sql.Blob;

public class StringTest {
	public static void main(String[] args) {
		StringBuffer s = new StringBuffer();
		for(int i = 0 ; i<2750; i++){
			s.append("a");
			
		}
		System.out.println(s.length());
		System.out.println(s);
//		Blob b = new ;
	}
	
}
