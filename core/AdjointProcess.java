package core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

public class AdjointProcess {

	public static LinkedBlockingQueue<String> in = new LinkedBlockingQueue<String>();
	public static LinkedBlockingQueue<String> out = new LinkedBlockingQueue<String>();
	public static LinkedBlockingQueue<String> error = new LinkedBlockingQueue<String>();

	public static void Invoke(String cmd) {
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			InputStream ins = p.getInputStream();
			OutputStream outs = p.getOutputStream();
			InputStream errors = p.getErrorStream();
			AdjointThread atin = new AdjointThread(ins, in, "in");
			AdjointThread atout = new AdjointThread(outs, out, "out");
			AdjointThread aterror = new AdjointThread(errors, error, "error");
			atin.start();
			atin.join(Constants.JOIN);
			atout.start();
			atout.join(Constants.JOIN);
			aterror.start();
			aterror.join(Constants.JOIN);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
