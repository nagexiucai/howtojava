package unittest;

public class Verifying {

	public static void main(String[] args) throws InterruptedException {
		// 获取系统字符编码
		System.out.println(System.getProperty("file.encoding"));

		// 报异常会终止任意嵌套层的循环
		while (true) {
			System.out.println("alive");
			for (int i=0; i<9527; i++) {
				System.out.println(i);
				if (i == 4096)
					throw new InterruptedException("break loop by an exception");
			}
		}
	}
}
