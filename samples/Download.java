package samples;

import java.io.UnsupportedEncodingException;

import core.AdjointProcess;

public class Download {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		System.out.println("Hello World");
		String cmd = "ping www.baidu.com";
		AdjointProcess.Invoke(cmd);
		String o = "origo";
		try {
			AdjointProcess.out.put("empty");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while (true) {
			o = AdjointProcess.in.poll();
			if (o != null)
				System.out.println(o);
			o = AdjointProcess.error.poll();
			if (o != null)
				System.out.println(o);
		}
	}
}
