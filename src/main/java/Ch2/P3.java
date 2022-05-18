package Ch2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * @Author xaohii
 * @Date 2022/4/23 12:37
 */


// 泛型学习



public class P3 {
	public static void main(String[] args) {
		// 字符串数组

//		String[] s = new String[20];
//		for (int i=0;i<s.length;i++){
//			s[i] = String.valueOf(i*3);
//		}
//		ArrayList<String> list = sample(s, 5);
//		for (Object o:list){
//			System.out.println(o.toString());
//		}

		add(1,2);

	}
	// 泛型方法
	public static <T extends Number> void add(T a, T b){
		System.out.println(a.doubleValue()+b.doubleValue());


	}
	// 泛型方法
	public static <T extends Object> ArrayList<T> sample(T[] a, int count){
		ArrayList<T> list = new ArrayList<>();
		Random random = new Random();
		int arrayLen = a.length;
		HashSet<Integer> hashSet = new HashSet<>();
		while (hashSet.size() != count){
			int index = random.nextInt(arrayLen);
			if(!hashSet.contains(index)){
				hashSet.add(index);
				list.add(a[index]);
			}

		}
		return list;
	}
}
class Test{
	public <T extends Number> double add(T a, T b){
		System.out.println(a+"+"+b+"="+(a.doubleValue()+b.doubleValue()));
		return a.doubleValue()+b.doubleValue();
	}
}
