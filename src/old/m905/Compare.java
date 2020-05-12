package m905;

public class Compare {
	public static void main(String[] args) {
		String a = new String("123");
		String b = new String("123");
		String c = a;
		String d = "123";
		//==俩个对象的引用地址是否相等
 		System.out.println("a==b-----"+(a==b));
 		//equals 俩个引用所指的内容是否相等
 		System.out.println("a.equals(b)---"+a.equals(b));
 		System.out.println("a==c"+(a==c));
 		System.out.println("a.equals(c)---"+a.equals(c));
 		System.out.println("a.equals(d)---"+a.equals(d));
	}
}
