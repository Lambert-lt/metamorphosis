package m0419;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import m0417.SortUtil;
import m0417.UserInfo;





public class SearchComparator implements Comparator<Search> {



	/**�򵥣������Դ����ġ���Ӣ�Ļ�����Ӣ�Ļ�Ͻ�������**/
	public int compare(Search s1, Search s2) {
		return s1.getName().compareToIgnoreCase(s2.getName());
	} 

	public List<Search> sortListByType(List<Search> list)
	{


		List engList=new ArrayList();
		List chaList=new ArrayList();


		if(list!=null&&list.size()>0)
		{


			for(int i=0;i<list.size();i++)
			{


				Search search=(Search)list.get(i);
				String name=search.getName();
				/**
				 *  ���Ӣ�����ȣ��������IF����Ϊ��
				 *  isContainsHanyu(name.substring(0,1))&&isContainsHanyu(name)
				 **/
				if(isContainsHanyu(name))
				{
					chaList.add(search);
				}
				else
				{
					engList.add(search);
				}


			}


		}


		if(chaList.size()>0)
		{
			PinyinComparator pinyin=new PinyinComparator();
			Collections.sort(chaList,pinyin);
		}


		if(engList.size()>0)
		{
			Collections.sort(engList,this);
		}


		list=new ArrayList();
		list=copy(list,engList);
		list=copy(list,chaList);


		return list;
	}

	//��һ�������е�Ԫ�ظ��Ƶ���һ��������
	public List copy(List sourceList,List copyList)
	{
		if(copyList!=null)
		{
			for(int i=0;i<copyList.size();i++)
			{
				sourceList.add(copyList.get(i));
			}
		}
		return sourceList;
	}


	//����ַ����Ƿ��������
	public boolean isContainsHanyu(String str)
	{
		boolean flag=false;
		Pattern  pattern   =   Pattern.compile("[\\u4e00-\\u9fa5]",Pattern.CANON_EQ);     
		Matcher  matcher   =   pattern.matcher(str);  


		if(matcher.find())   
		{   
			flag=true;
		}  


		return flag;

	}


	public static void main(String[] args)
	{
//		String s = "test����";
//
		SearchComparator comparator=new SearchComparator();
//		comparator.isContainsHanyu(s);
		
		List<Search> li = new ArrayList<Search>();
		
		Search search = new Search();
		search.setSearchName("a");
		
		Search search1 = new Search();
		search1.setSearchName("b");
		
		Search search2 = new Search();
		search2.setSearchName("��");
		
		Search search3 = new Search();
		search3.setSearchName("������");
		
		Search search4 = new Search();
		search4.setSearchName("����");
		
		Search search5 = new Search();
		search5.setSearchName("a1");
		
		Search search6 = new Search();
		search6.setSearchName("a10");
		
		Search search7 = new Search();
		search7.setSearchName("a2");
		
		Search search8 = new Search();
		search8.setSearchName("����1");
		
		Search search9 = new Search();
		search9.setSearchName("����1a");
		
		Search search10 = new Search();
		search10.setSearchName("����1b");
		
		li.add(search);
		li.add(search1);
		li.add(search2);
		li.add(search3);
		li.add(search4);
		li.add(search5);
		li.add(search6);
		li.add(search7);
		li.add(search8);
		li.add(search9);
		li.add(search10);
		
		for (Search se : li) {  
			String tempName = SortUtil.changeIntToSpecifyLength(se.getSearchName());  
			se.setName(tempName);
		} 
		
		List<Search> li1 = comparator.sortListByType(li);
		for (int i = 0; i < li1.size(); i++) {
			System.out.println(li1.get(i).getSearchName());
		}
	}

}