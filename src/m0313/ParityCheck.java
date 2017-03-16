package m0313;

import java.util.Scanner;

public class ParityCheck {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入要判断的值");
		int a = scan.nextInt();
		String result = (a%2==0)?"偶数":"奇数";
		System.out.println(result);
		scan.close();
		
		//左移n位 就是乘以2的n次方
		System.out.println("21*16 = "+21*16);
		
		System.out.println("21<<4 = "+(21<<4));
	}
}
