package m0323;

public class NineTable {

	public static void main(String[] args) {
		// ����һ�����׷���
		int arr[][] = new int[3][3];
		// ��3�е����±�
		int a = 2;
		// �ڶ��е����±�
		int b = 3 / 2;

		// �����鸳ֵ
		for (int i = 1; i <= 9; i++) {
			arr[a++][b++] = i;                     
			if (i % 3 == 0) {
				a = a - 2;
				b = b - 1;
			} else {
				a = a % 3;
				b = b % 3;

			}
		}
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}

	}

}
