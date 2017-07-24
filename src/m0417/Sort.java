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
		String[] arrays = new String[] { "gyu","��","����", "ga","a",".","1","��ͬ", "zf", "�յ�1", "sdf", "�ط�", "���ȷ�", "����", "���Ը���", "������", "�ϵ�Ͷ��", "�͹���" };

		/*�����������Ի���*/
//		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.SIMPLIFIED_CHINESE);
		Arrays.sort(arrays,   com.ibm.icu.text.Collator.getInstance(com.ibm.icu.util.ULocale.ENGLISH));


		for (int i=0;i<arrays.length;i++) {

			System.out.println(arrays[i]);
		}
		
		List<String> a = new ArrayList<String>();
		
		a.add("a");
		a.add("ƻ��2");
		a.add("ƻ��1");
		a.add("ƻ��");
		a.add("z");
		
		sort(a);
		for(int i=0; i < a.size();i++){
//		System.out.println(a.get(i));
		}
		}
}