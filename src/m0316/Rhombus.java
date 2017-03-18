package m0316;

public class Rhombus {

	//for循环打印菱形
	public static void printRhombus() {

		for (int i = 0; i < 5; i++) {
			for(int j = 5; j > i; j--){
				System.out.print(" ");
			}
			for(int j = 0; j < 2*i+1; j++){
				System.out.print("*");
			}
			System.out.println("");
		}

		for (int i = 0; i < 4; i++) {
			for(int j = 0; j < i+2; j++){
				System.out.print(" ");
			}
			for(int j = 6; j > 2*i-1; j--){
				System.out.print("*");
			}
			System.out.println("");
		}

	}

	//用while 语句计算 1+1/2!+1/3!...1/20!之和
	public static void factorial(){
		int n = 1;
		float m = 1;
		float sum = 0;
		while(n <= 20){
			sum = sum + m/factorialMath(n);
			System.out.println("----"+sum);
			n++;
		}
		System.out.println(sum);
	}

	//求阶乘的方法
	public static Integer factorialMath(int n){
		int sum = n;
		for(int i = 0; i < n;){
			if(n==1){
				sum =  sum*1;
			}else{
				sum = sum*(n-1);
			}
			n--;
		}

//		System.out.println(sum);
		return sum;
	}

	public static void main(String[] args) {
		printRhombus();
//		factorialMath(5);
		factorial();
	}
}
