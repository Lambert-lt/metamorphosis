package m906;

public class HanoiTower {
	
	public static void moveDish(int dish,String from,String inter,String to){
		if(dish == 1){
			System.out.println("��"+from+"�ƶ�1�����ӵ�"+to);
		}else{
			moveDish(dish-1, from, to, inter);
			System.out.println("��"+from+"�ƶ�"+dish+"�����ӵ�"+to);
			moveDish(dish-1, inter, from, to);
		}
	}
	
	public static void main(String[] args) {
		int dish = 3;
		moveDish(dish, "A", "B", "C");
	}
}
