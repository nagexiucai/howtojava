package samples;

import core.AdjointProcess;

public class Download {
	
	public static void main(String[] args) {
		
		System.out.println("!!Hello World!!");
		String cmd = "ping www.baidu.com";
		AdjointProcess.Invoke(cmd);
		while (true) {
			System.out.println(AdjointProcess.in.poll());
			try {
				AdjointProcess.out.put("empty");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(AdjointProcess.error.poll());
		}
	}
}
