package unittest;

public class Verifying {

	public static void main(String[] args) throws InterruptedException {
		// ��ȡϵͳ�ַ�����
		System.out.println(System.getProperty("file.encoding"));

		// ���쳣����ֹ����Ƕ�ײ��ѭ��
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
