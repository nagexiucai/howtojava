package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

public class AdjointThread extends Thread {

	public boolean bibi = true;
	private Object stream = null;
	private LinkedBlockingQueue<String> queue = null;

	public AdjointThread(Object stream, LinkedBlockingQueue<String> queue, String name) {
		super(name);
		this.stream = stream;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			if (this.stream instanceof InputStream) {
				InputStreamReader isr = new InputStreamReader((InputStream) this.stream, Constants.ENCODING);
				BufferedReader br = new BufferedReader(isr);
				String s = null;
				while (this.bibi) {
					System.out.println(this.getName());
					s = br.readLine();
					if (s == null) {
						System.out.println(this.getName() + " quit");
						break;
					}
					try {
						queue.put(s);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			else if (this.stream instanceof OutputStream) {
				String s = null;
				while (this.bibi) {
					System.out.println(this.getName());
					try {
						s = this.queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
						continue;
					}
					if (Constants.QUIT.equals(s)) {
						System.out.println(this.getName() + " quit");
						break;
					}
					((OutputStream) this.stream).write(s.getBytes());
				}
			}
			else {
				System.out.println("what a shame");
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.bibi = false;
		}
	}
}
