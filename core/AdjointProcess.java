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
			AdjointThread atin = new AdjointThread(ins, in);
			AdjointThread atout = new AdjointThread(outs, out);
			AdjointThread aterror = new AdjointThread(errors, error);
			atin.start();
			atout.start();
			aterror.start();
			try {
				p.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ins.close();
			outs.close();
			errors.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
