package m0313;

import java.util.Scanner;

public class ParityCheck {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("������Ҫ�жϵ�ֵ");
		int a = scan.nextInt();
		String result = (a%2==0)?"ż��":"����";
		System.out.println(result);
		scan.close();
	}
}
