package Ch6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author xaohii
 * @Date 2022/4/27 10:50
 */
public class P1 {
	public static void main(String[] args) {
//		ArrayList<String> list = new ArrayList<>();
//		LinkedList<String> linkedList = new LinkedList<>();
//		for (int i=0;i<10;i++){
//			list.add(String.valueOf(i));
//			linkedList.add(String.valueOf(i));
//		}
//		String c = list.get(1);
//		System.out.println(linkedList.get(6));
//		System.out.println(c);
//		System.out.println(10>>1);
		Stack<Integer> stack = new Stack<>();
		for (int i=0;i<10;i++){
			stack.add(i);
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}


	}
}
