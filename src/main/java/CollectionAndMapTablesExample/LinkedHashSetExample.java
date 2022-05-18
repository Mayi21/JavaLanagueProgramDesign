package CollectionAndMapTablesExample;

import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @Author xaohii
 * @Date 2022/3/11 10:55
 */
public class LinkedHashSetExample {
	public static void main(String[] args) {
		LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
		TreeSet<Integer> treeSet = new TreeSet<>();
		for (int i=20;i>0;i--){
			linkedHashSet.add(i%10);
			treeSet.add(i%10);
		}
		for (Integer i:linkedHashSet){
			System.out.println(i);
		}
		System.out.println("-----");
		for (Integer i:treeSet){
			System.out.println(i);
		}
	}
}
