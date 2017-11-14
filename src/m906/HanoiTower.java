package m906;

public class HanoiTower {
	
	public static void moveDish(int dish,String from,String inter,String to){
		if(dish == 1){
			System.out.println("从"+from+"移动1号盘子到"+to);
		}else{
			moveDish(dish-1, from, to, inter);
			System.out.println("从"+from+"移动"+dish+"号盘子到"+to);
			moveDish(dish-1, inter, from, to);
		}
	}
	
	public static void main(String[] args) {
		int dish = 3;
		moveDish(dish, "A", "B", "C");
	}
}
