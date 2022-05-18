package Ch1;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * @Author xaohii
 * @Date 2022/4/21 16:21
 */
public class P5 {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i=0;i<1000;i++){
			list.add(i);
		}
		ListIterator<Integer> iterator = list.listIterator();
		while (iterator.hasNext()){
			if (iterator.next() % 10 == 0) {
				iterator.set(99);
			}
		}
		iterator = list.listIterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
