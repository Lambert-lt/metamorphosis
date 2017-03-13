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
	}
}
