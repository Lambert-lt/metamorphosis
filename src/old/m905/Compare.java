package m905;

public class Compare {
	public static void main(String[] args) {
		String a = new String("123");
		String b = new String("123");
		String c = a;
		String d = "123";
		//==������������õ�ַ�Ƿ����
 		System.out.println("a==b-----"+(a==b));
 		//equals ����������ָ�������Ƿ����
 		System.out.println("a.equals(b)---"+a.equals(b));
 		System.out.println("a==c"+(a==c));
 		System.out.println("a.equals(c)---"+a.equals(c));
 		System.out.println("a.equals(d)---"+a.equals(d));
	}
}
