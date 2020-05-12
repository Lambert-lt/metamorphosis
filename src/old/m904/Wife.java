package m904;

public class Wife extends Person{
	
//	public Wife() {
////		super("Aimy");
//		System.out.println("Wife---------");
//	}
	
	public void eat(){
		System.out.println("Wife ----- eat");
	}
	
	public static void sleep(){
		System.out.println("Wife ----- sleep");
	}
	
	void shop(){
		System.out.println("Wife ----- shop");
	} 
	
	public static void main(String[] args) {
		Wife w = new Wife();
		w.name ="sss";
		System.out.println(w.name);
	}
}
