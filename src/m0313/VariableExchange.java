package m0313;

import java.util.Scanner;

public class VariableExchange {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("请输入变量a的值");
		Long a = scan.nextLong();
		System.out.println("请输入变量b的值");
		Long b = scan.nextLong();
		System.out.println("a = "+a +"\n b = "+b);
		System.out.println("执行变量互换。。。。。。。。。。。");
		a = a^b;
		b = b^a;
		a = a^b;
		System.out.println("a = "+a +"\n b = "+b);
		
		scan.close();
	}

}
