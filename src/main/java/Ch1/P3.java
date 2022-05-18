package Ch1;

import java.util.ArrayList;
import java.util.Collections.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xaohii
 * @Date 2022/4/21 14:27
 */
// 测试ConcurrentHashMap
public class P3 {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}
