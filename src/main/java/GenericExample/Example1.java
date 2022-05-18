package GenericExample;

import java.util.Random;

/**
 * @Author xaohii
 * @Date 2022/3/11 10:39
 */
public class Example1 {
	public static void main(String[] args) {
		String[] strings = new String[100];
		for (int i=0;i<100;i++){
			strings[i] = "i"+i;
		}
		int randSampleCount = 10;
		RandSample<String> randSample = new RandSample<String>(randSampleCount);
		String[] randSamples = (String[]) randSample.get(strings);
		for (String i:randSamples){
			System.out.println(i);
		}

	}
}
class RandSample<E>{
	private E[] array;
	private int count;
	@SuppressWarnings("unchecked")
	public RandSample(int c){
		count = c;
		array = (E[])new Object[count];

	}
	public E[] get(E t[]){
		int len = t.length;
		Random random = new Random();
		for (int i=0;i<count;i++){
			random.setSeed(System.currentTimeMillis());
			int r = random.nextInt(len);
			array[i] = t[r];
		}
		return array;
	}
}