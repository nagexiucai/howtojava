package samples;

public class Download {

	private String url = null;
	private String savePath = null;

	public Download(String url, String savePath) {
		this.url = url;
		this.savePath = savePath;
	}

	public boolean download() {
		return true;
	}

	public boolean startFtpServer() {
		// TODO: ʹ��Apache FtpServer����Ƕ��ʽFTP������
		return true;
	}

	public static void main(String args) {
		String url = "";
		String savePath = "";
		Download dl = new Download(url, savePath);
		dl.startFtpServer();
		dl.download();
	}
}
