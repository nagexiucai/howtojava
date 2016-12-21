package samples;

import java.io.UnsupportedEncodingException;
import core.AdjointProcess;
import core.Constants;

public class Ping {

	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {

		System.out.println("=====PING=====");
		String cmd = "ping www.baidu.com";
		AdjointProcess.Invoke(cmd);
		String o = "origo";
		try {
			AdjointProcess.out.put("empty");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long starttime = System.currentTimeMillis();
		while (Constants.TIMEOUT >= System.currentTimeMillis() - starttime) {
			o = AdjointProcess.in.poll();
			if (o != null)
				System.out.println(o);
			o = AdjointProcess.error.poll();
			if (o != null)
				System.out.println(o);
		}
		AdjointProcess.out.put(Constants.QUIT);
	}
}
