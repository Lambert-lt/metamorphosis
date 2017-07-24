package m0417;

import java.util.ArrayList;  
import java.util.Collections;  
import java.util.List;  

public class Main {  
	public static void main(String[] args) {  

		List<UserInfo> list = new ArrayList<UserInfo>();  

		UserInfo info1 = new UserInfo();  
		info1.setUserName("测试1b3");  

		UserInfo info2 = new UserInfo();  
		info2.setUserName("测试10");  

		UserInfo info3 = new UserInfo();  
		info3.setUserName("测试2");  

		UserInfo info4 = new UserInfo();  
		info4.setUserName("测试20");  

		UserInfo info5 = new UserInfo();  
		info5.setUserName("测试2b");  

		UserInfo info6 = new UserInfo();  
		info6.setUserName("氨基酸"); 
		
		UserInfo info7 = new UserInfo();  
		info7.setUserName("啫喱");  
		
		UserInfo info8 = new UserInfo();  
		info8.setUserName("网易"); 
		
		UserInfo info9 = new UserInfo();  
		info9.setUserName("嚟");  

		list.add(info1);  
		list.add(info2);  
		list.add(info3);  
		list.add(info4);  
		list.add(info5);  
		list.add(info6);  
		list.add(info8);
		list.add(info7);
		list.add(info9);

		//先将字符串中的数组转换为指定长度�?数字  
		for (UserInfo userInfo : list) {  
			String tempName = SortUtil.changeIntToSpecifyLength(userInfo.getUserName());  
			userInfo.setTempUserName(tempName);  
		}  

		//对转换后的数据进行排�?  
		HanziComparator hanziComparator = new HanziComparator();  
		Collections.sort(list, hanziComparator);  
		for(int i=0;i<list.size();i++){
			System.out.println("Result:\n" + list.get(i));  
		}
	}  
}  
