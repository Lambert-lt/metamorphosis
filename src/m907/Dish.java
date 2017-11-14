package m907;

public class Dish {
	
	public static void MoveDish(int leve ,String from,String inter,String to) {
		if(leve == 1){
			System.out.println("从"+from+"移动1号盘到"+to);
		}else{
			MoveDish(leve-1, from, to, inter);
			System.out.println("从"+from+"移动"+leve+"号盘到"+to+"");
			MoveDish(leve-1, inter,from, to);
		}
	}

	public static void main(String[] args) {
		int dish = 3;
//		MoveDish(dish, "A", "B", "C");
		String a="Y";
		System.out.println(Boolean.parseBoolean(a));
		
	}
}
