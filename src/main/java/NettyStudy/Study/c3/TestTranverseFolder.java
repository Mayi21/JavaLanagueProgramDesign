package NettyStudy.Study.c3;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xaohii
 * @Date 2022/5/12 8:39
 */
public class TestTranverseFolder {
	public static void main(String[] args) throws IOException {
		m2();

	}

	public static void m2() throws IOException {
		AtomicInteger dirCount = new AtomicInteger();
		AtomicInteger fileCount = new AtomicInteger();
		Files.walkFileTree(Paths.get("C:\\Study\\Java\\JavaLanagueProgramDesign"), new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (file.toString().endsWith(".jar")){
					System.out.println(file);
					fileCount.incrementAndGet();
				}

				return super.visitFile(file, attrs);
			}

		});
		System.out.println("dir count "+ dirCount + " and file count "+fileCount);
	}

	public static void m1() throws IOException {
		AtomicInteger dirCount = new AtomicInteger();
		AtomicInteger fileCount = new AtomicInteger();

		Files.walkFileTree(Paths.get("C:\\Study\\Java\\JavaLanagueProgramDesign"), new SimpleFileVisitor<Path>(){
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("======>"+dir);
				dirCount.incrementAndGet();
				return super.preVisitDirectory(dir, attrs);
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(file);
				fileCount.incrementAndGet();
				return super.visitFile(file, attrs);
			}

		});
		System.out.println("dir count "+ dirCount + " and file count "+fileCount);
	}
}
