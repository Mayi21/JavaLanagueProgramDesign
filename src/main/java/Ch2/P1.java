package Ch2;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @Author xaohii
 * @Date 2022/4/23 10:22
 */
public class P1 {
	public static void main(String[] args) throws Exception{
		FileInputStream file = new FileInputStream("src/main/java/Ch2/peizhi");
		Properties properties = new Properties();
		properties.load(file);
		String className = properties.get("classpath").toString();
		String methodName = properties.get("method").toString();
		System.out.println(className);

		Class clz = Class.forName(className);
		Object o = clz.newInstance();
		Method method = clz.getMethod(methodName);
		method.invoke(o);


	}
}
