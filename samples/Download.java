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
		// TODO: 使用Apache FtpServer启动嵌入式FTP服务器
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
