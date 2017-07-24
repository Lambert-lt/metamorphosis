package m0417;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Sort {
	
	private static void sort(List<String> data) {

		Collections.sort(data, new Comparator<String>() {

			public int compare(String o1, String o2) {

				String a = o1;
				String b = o2;

				return a.compareTo(b);
			}

		});
	}
	
	public static void main(String[] args) {
		String[] arrays = new String[] { "gyu","钛","网易", "ga","a",".","1","大同", "zf", "收到1", "sdf", "地方", "三等分", "的人", "反对高铁", "泛代数", "上的投入", "和国家" };

		/*设置排序语言环境*/
//		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE);
		Arrays.sort(arrays,   com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.ENGLISH));


		for (int i=0;i<arrays.length;i++) {

			System.out.println(arrays[i]);
		}
		
		List<String> a = new ArrayList<String>();
		
		a.add("a");
		a.add("苹果2");
		a.add("苹果1");
		a.add("苹方");
		a.add("z");
		
		sort(a);
		for(int i=0; i < a.size();i++){
//		System.out.println(a.get(i));
		}
		}
}