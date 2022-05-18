package Ch2;

/**
 * @Author xaohii
 * @Date 2022/4/23 12:56
 */
public class P4 {
	public static void main(String[] args) {
		Point<String> point = new Point<String>();
		point.setA("123");
		point.toMyString();

	}
}

class Point<T>{
	private T a;
	Point(T a){
		this.a = a;
	}
	Point(){

	}
	public T getA(){
		return a;
	}
	public void setA(T a){
		this.a = a;
	}
	public void toMyString(){
		System.out.println(this.a.getClass());
	}
}