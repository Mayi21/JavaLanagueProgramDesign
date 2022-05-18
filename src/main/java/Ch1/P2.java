package Ch1;

import javax.xml.crypto.Data;

/**
 * @Author xaohii
 * @Date 2022/4/21 14:03
 */
public class P2 {
	public static void main(String[] args) {
		Parent parent = new Parent();
		parent.run();
		Parent parent1 = new Child();
		parent1.run();
	}
}
class Parent{
	public void run(){
		System.out.println("parent");
	}
	public void Now(){
		long l = System.currentTimeMillis();
		System.out.println(l);
	}
}
class Child extends Parent{
	@Override
	public void run(){
		System.out.println("child");
	}
}
