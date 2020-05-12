package m907;

import java.util.Arrays;  
import java.util.Collections;  
import java.util.List;  

public class Pager<T> {  
	  
    /** 
     * ÿҳ��ʾ���� 
     */  
    private int pageSize;  
    /** 
     * ԭ���� 
     */  
    private List<T> data;  
  
    private Pager(List<T> data, int pageSize) {  
        if (data == null || data.isEmpty()) {  
            throw new IllegalArgumentException("data must be not empty!");  
        }  
  
        this.data = data;  
        this.pageSize = pageSize;  
    }  
  
    /** 
     * ������ҳ�� 
     * 
     * @param data ��Ҫ��ҳ������ 
     * @param pageSize ÿҳ��ʾ���� 
     * @param <T> ҵ����� 
     * @return ��ҳ�� 
     */  
    public static <T> Pager<T> create(List<T> data, int pageSize) {  
        return new Pager<>(data, pageSize);  
    }  
  
    /** 
     * �õ���ҳ������� 
     * 
     * @param pageNum ҳ�� 
     * @return ��ҳ���� 
     */  
    public List<T> getPagedList(int pageNum) {  
        int fromIndex = (pageNum - 1) * pageSize;  
        if (fromIndex >= data.size()) {  
            return Collections.emptyList();  
        }  
  
        int toIndex = pageNum * pageSize;  
        if (toIndex >= data.size()) {  
            toIndex = data.size();  
        }  
        return data.subList(fromIndex, toIndex);  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public List<T> getData() {  
        return data;  
    }  
  
    public static void main(String[] args) {  
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};  
        List<Integer> list = Arrays.asList(array);  
  
        Pager<Integer> pager = Pager.create(list, 10);  
  
        List<Integer> page1 = pager.getPagedList(1);  
        System.out.println(page1);  
  
        List<Integer> page2 = pager.getPagedList(2);  
        System.out.println(page2);  
  
        List<Integer> page3 = pager.getPagedList(3);  
        System.out.println(page3);  
    }  
} 
