package m0313;

import java.util.Scanner;

public class VariableExchange {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("���������a��ֵ");
		Long a = scan.nextLong();
		System.out.println("���������b��ֵ");
		Long b = scan.nextLong();
		System.out.println("a = "+a +"\n b = "+b);
		System.out.println("ִ�б�����������������������������");
		a = a^b;
		b = b^a;
		a = a^b;
		System.out.println("a = "+a +"\n b = "+b);
		
		scan.close();
	}

}
