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
		
		//����nλ ���ǳ���2��n�η�
		System.out.println("21*16 = "+21*16);
		
		System.out.println("21<<4 = "+(21<<4));
	}
}
