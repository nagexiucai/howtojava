package core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.LinkedBlockingQueue;

public class AdjointThread extends Thread {

	public boolean bibi = true;
	private Object stream = null;
	private LinkedBlockingQueue<String> queue = null;

	public AdjointThread(Object stream, LinkedBlockingQueue<String> queue) {
		this.stream = stream;
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			if (this.stream instanceof InputStream) {
				int b = 0;
				StringBuffer sb = new StringBuffer();
				while (this.bibi && ((b = ((InputStream) this.stream).read()) != Constants.EOF)) {
					sb.append((char)b);
					if (b == Constants.NL) {
						try {
							queue.put(sb.toString());
						} catch (InterruptedException e) {
							e.printStackTrace();
							continue;
						}
						sb.setLength(0);
					}
				}
			}
			else if (this.stream instanceof OutputStream) {
				String s = null;
				while (this.bibi) {
					try {
						s = this.queue.take();
					} catch (InterruptedException e) {
						e.printStackTrace();
						continue;
					}
					((OutputStream) this.stream).write(s.getBytes());
				}
			}
			else {
				System.out.println("what a shame!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
